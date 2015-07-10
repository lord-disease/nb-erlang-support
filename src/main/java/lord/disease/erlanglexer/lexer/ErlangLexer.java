/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lord.disease.erlanglexer.lexer;

import org.antlr.v4.runtime.Token;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author alain
 */
public class ErlangLexer implements Lexer<ErlangTokenId> {
    private final lord.disease.erlanglexer.ErlangLexer erlangLexer;
    private final LexerRestartInfo<ErlangTokenId> info;

    public ErlangLexer(LexerRestartInfo<ErlangTokenId> info) {
        this.info = info;
        AntlrCharStream charStream = new AntlrCharStream(info.input(), "ErlangEditor");
        erlangLexer = new lord.disease.erlanglexer.ErlangLexer(charStream);
    }

    @Override
    public org.netbeans.api.lexer.Token<ErlangTokenId> nextToken() {
        Token token = erlangLexer.nextToken();
        if (token.getType() != lord.disease.erlanglexer.ErlangLexer.EOF) {
            ErlangTokenId tokenId = ErlangLanguageHierarchy.getToken(token.getType());
            return info.tokenFactory().createToken(tokenId);
        }
        return null;
    }

    @Override
    public void release() {
    }

    @Override
    public Object state() {
        return null;
    }
    
}
