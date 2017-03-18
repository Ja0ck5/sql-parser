package com.duowan.lexer;


import static com.duowan.parser.LayoutCharacters.EOI;
//import static com.alibaba.druid.sql.parser.CharTypes.isFirstIdentifierChar;
//import static com.alibaba.druid.sql.parser.CharTypes.isWhitespace;
import static com.duowan.token.Token.COLONCOLON;
import static com.duowan.token.Token.COLONEQ;
import static com.duowan.token.Token.COMMA;
import static com.duowan.token.Token.EOF;
import static com.duowan.token.Token.LBRACE;
import static com.duowan.token.Token.LPAREN;
import static com.duowan.token.Token.RBRACE;
import static com.duowan.token.Token.RBRACKET;
import static com.duowan.token.Token.RPAREN;

import org.hamcrest.core.SubstringMatcher;

import com.duowan.keywords.Keywords;
import com.duowan.token.Token;

/**
 * 词法分析器
 * @author Jack
 *
 */
public class Lexer {
	protected final String text;
    protected int          pos;
    protected int          mark;

    protected char         ch;

    protected char[]       buf;
    protected int          bufPos;

    protected Token        token;

    protected Keywords     keywods      = Keywords.DEFAULT_KEYWORDS;

    protected String       currentStrVal;
    
    protected int            line         = 0;
    
    protected int            lines        = 0;

	public Lexer(String inSql) {
		this.text = inSql;
		this.pos = -1;
		this.mark = 0;
		scanChar();
	}
    
    public final char charAt(int index) {
        if (index >= text.length()) {
            return EOI;
        }

        return text.charAt(index);
    }
	
	protected final void scanChar() {
        ch = charAt(++pos);
    }
    
	public final Token token() {
        return token;
    }
	
    public boolean isEOF() {
        return pos >= text.length();
    }
	
	public final void nextToken() {
        bufPos = 0;
        
        for (;;) {
        	//空格
        	if (Character.isWhitespace(ch)) {
            	mark++;
            	if(pos - mark > 0){
            		// 獲取到單詞
            		currentStrVal = text.substring(mark-1,pos);
            		//同步標記
            		mark = pos;
            		//判斷是否為關鍵字
                    Token tok = keywods.getKeyword(currentStrVal);
                    if (tok != null) {
                        token = tok;
                    } else {
                        token = Token.IDENTIFIER;
                    }
                    return;
            	}
            }
        	
        	switch(ch){
        	 case '(':
                 scanChar();
                 mark++;
                 token = LPAREN;
                 return;
             case ')':
                 scanChar();
                 mark++;
                 token = RPAREN;
                 return;
             case ',':
            	 scanChar();
            	 mark++;
                 token = COMMA;
                 return;
             default:
                 if (isEOF())
                 { 
                	 token = EOF;
                	 return;
                 }
        	}
        	
            scanChar();
        }
        

    }
    
    
}
