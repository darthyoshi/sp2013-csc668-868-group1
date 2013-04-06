package org.openmrs.module.basicmodule.dsscompiler.lexer;
 
/**
 *  This file is automatically generated<br>
 *  it contains the table of mappings from token
 *  constants to their Symbols
*/
public class TokenType {
   public static java.util.HashMap<Tokens,Symbol> tokens = new java.util.HashMap<Tokens,Symbol>();
   public TokenType() {
     tokens.put(Tokens.Program, Symbol.symbol("program",Tokens.Program));
     tokens.put(Tokens.Int, Symbol.symbol("int",Tokens.Int));
     tokens.put(Tokens.BOOLean, Symbol.symbol("boolean",Tokens.BOOLean));
     tokens.put(Tokens.If, Symbol.symbol("if",Tokens.If));
     tokens.put(Tokens.Then, Symbol.symbol("then",Tokens.Then));
     tokens.put(Tokens.Else, Symbol.symbol("else",Tokens.Else));
     tokens.put(Tokens.Elsif, Symbol.symbol("elsif",Tokens.Elsif));
     tokens.put(Tokens.While, Symbol.symbol("while",Tokens.While));
     tokens.put(Tokens.For, Symbol.symbol("for",Tokens.For));
     tokens.put(Tokens.Function, Symbol.symbol("function",Tokens.Function));
     tokens.put(Tokens.Return, Symbol.symbol("return",Tokens.Return));
     tokens.put(Tokens.New, Symbol.symbol("new",Tokens.New));
     tokens.put(Tokens.In, Symbol.symbol("in",Tokens.In));
     tokens.put(Tokens.True, Symbol.symbol("true",Tokens.True));
     tokens.put(Tokens.False, Symbol.symbol("false",Tokens.False));
     tokens.put(Tokens.Null, Symbol.symbol("null",Tokens.Null));
     tokens.put(Tokens.Identifier, Symbol.symbol("<id>",Tokens.Identifier));
     tokens.put(Tokens.INTeger, Symbol.symbol("<int>",Tokens.INTeger));
     tokens.put(Tokens.Float, Symbol.symbol("<float>",Tokens.Float));
     tokens.put(Tokens.STRing, Symbol.symbol("<string>",Tokens.STRing));
     tokens.put(Tokens.Object, Symbol.symbol("Object",Tokens.Object));
     tokens.put(Tokens.LeftBrace, Symbol.symbol("{",Tokens.LeftBrace));
     tokens.put(Tokens.RightBrace, Symbol.symbol("}",Tokens.RightBrace));
     tokens.put(Tokens.LeftParen, Symbol.symbol("(",Tokens.LeftParen));
     tokens.put(Tokens.RightParen, Symbol.symbol(")",Tokens.RightParen));
     tokens.put(Tokens.Comma, Symbol.symbol(",",Tokens.Comma));
     tokens.put(Tokens.Dot, Symbol.symbol(".",Tokens.Dot));
     tokens.put(Tokens.Assign, Symbol.symbol(":=",Tokens.Assign));
     tokens.put(Tokens.Equal, Symbol.symbol("==",Tokens.Equal));
     tokens.put(Tokens.NotEqual, Symbol.symbol("!=",Tokens.NotEqual));
     tokens.put(Tokens.Not, Symbol.symbol("not",Tokens.Not));
     tokens.put(Tokens.Less, Symbol.symbol("<",Tokens.Less));
     tokens.put(Tokens.LessEqual, Symbol.symbol("<=",Tokens.LessEqual));
     tokens.put(Tokens.Plus, Symbol.symbol("+",Tokens.Plus));
     tokens.put(Tokens.Minus, Symbol.symbol("-",Tokens.Minus));
     tokens.put(Tokens.Or, Symbol.symbol("|",Tokens.Or));
     tokens.put(Tokens.And, Symbol.symbol("&",Tokens.And));
     tokens.put(Tokens.Multiply, Symbol.symbol("*",Tokens.Multiply));
     tokens.put(Tokens.Power, Symbol.symbol("**",Tokens.Power));
     tokens.put(Tokens.Divide, Symbol.symbol("/",Tokens.Divide));
     tokens.put(Tokens.Concat, Symbol.symbol("||",Tokens.Concat));
     tokens.put(Tokens.Comment, Symbol.symbol("//",Tokens.Comment));
   }
}
