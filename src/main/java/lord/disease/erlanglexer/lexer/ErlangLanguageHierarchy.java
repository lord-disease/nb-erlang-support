/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lord.disease.erlanglexer.lexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author alain
 */
public class ErlangLanguageHierarchy extends LanguageHierarchy<ErlangTokenId> {

    private static final List<ErlangTokenId> tokens = new ArrayList<>();
    private static final Map<Integer, ErlangTokenId> idToToken = new HashMap<>();

    static {
        TokenType[] tokenTypes = TokenType.values();
        for (TokenType tokenType : tokenTypes) {
            tokens.add(new ErlangTokenId(tokenType.name(), tokenType.category, tokenType.id));
        }
        for (ErlangTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
        }
    }

    static synchronized ErlangTokenId getToken(int id) {
        return idToToken.get(id);
    }

    @Override
    protected synchronized Lexer<ErlangTokenId> createLexer(LexerRestartInfo<ErlangTokenId> info) {
        return new ErlangLexer(info);
    }

    @Override
    protected synchronized Collection<ErlangTokenId> createTokenIds() {
        return tokens;
    }

    @Override
    protected String mimeType() {
        return "text/x-erlang";
    }

}
