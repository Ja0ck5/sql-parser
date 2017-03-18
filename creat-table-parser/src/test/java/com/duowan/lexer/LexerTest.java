package com.duowan.lexer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.duowan.token.Token;

public class LexerTest {

	@Test
	public void testLexer() {
		String inSql = "\n   create table pet (name varchar(10) ,gender char(1),cerateTime date,cerateD date)";
		Lexer lexer = new Lexer(inSql);
		lexer.nextToken();
		System.out.printf("1 : %s\r\n", lexer.token);
		
		//判断是否为 create
		if(lexer.token() == Token.CREATE){
			System.out.println(" continue ---> next is table");
		}else{
			System.out.println("I'm gonna return,do not stop me!");
			return;
		}
		lexer.nextToken();
		System.out.printf("2 : %s\r\n",lexer.token);
		
		if(lexer.token() == Token.TABLE)
			System.out.println(" continue ---> next is tableName");
		
		lexer.nextToken();
		System.out.printf("3 : %s\r\n tableName : %s\r\n",lexer.token,lexer.currentStrVal);
		
		if(lexer.token() == Token.IDENTIFIER)
			System.out.println(" continue ---> next is Lparen");
		
		lexer.nextToken();
		System.out.printf("4 : %s\r\n",lexer.token);
		
		if(lexer.token() == Token.LPAREN)
			System.out.println(" continue ---> next is columns");
		
		//TODO 循環獲取 columns
		for(;;){
			//columns name
			lexer.nextToken();
			System.out.printf("loop1 : %s\r\n column name: %s\r\n ",lexer.token,lexer.currentStrVal);

			//columns dataType
			lexer.nextToken();
			System.out.printf("loop2 : %s\r\n data type: %s\r\n ",lexer.token,inSql.substring(lexer.mark-1, lexer.pos-1));
			
			lexer.mark = lexer.pos;
			
			//columns dataLen
			// 如果为左右括号以内的，获取括号内的值
			if(lexer.token() == Token.LPAREN)
				System.out.printf("loop3 :left: %s\r\n ",lexer.token);
			
			lexer.nextToken();
			if(lexer.isEOF())
				break;
			System.out.printf("loop4 : %s\r\n data length: %s\r\n ",lexer.token,inSql.substring(lexer.mark-1, lexer.pos-1));
			//判断值是否为数字
			
			if(lexer.token() == Token.RPAREN)
				lexer.mark = lexer.pos;
				System.out.printf("loop5 :right: %s\r\n ",lexer.token);
			//继续查
			lexer.nextToken();
			//not null 等关键词判断	
				
			// 如果为逗号
			if(lexer.token() == Token.COMMA){
				continue;
			}else{
				break;
			}
			
		}
		
		//判断engine partitions 等条件
		
		
		/*lexer.nextToken();
		System.out.printf("10 : %s\r\n column name: %s\r\n",lexer.token,lexer.currentStrVal);
		lexer.nextToken();
		System.out.printf("11 : %s\r\n",lexer.token);

		lexer.nextToken();
		System.out.printf("12 : %s\r\n",lexer.token);

		if(lexer.token() == Token.RPAREN)
			System.out.println("It is going down.");*/
		
	}

}
