/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lord.disease.erlanglexer.lexer;

import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author alain
 */
public class ErlangTokenId implements TokenId {
    private static final Language<ErlangTokenId> language = new ErlangLanguageHierarchy().language();
    private final int id;
    private final String name;
    private final String primaryCategory;

    public ErlangTokenId(String name, String primaryCategory, int id) {
        this.id = id;
        this.name = name;
        this.primaryCategory = primaryCategory;
    }

    
    @Override
    public String name() {
        return name;
    }

    @Override
    public int ordinal() {
        return id;
    }

    @Override
    public String primaryCategory() {
        return primaryCategory;
    }

    public static final Language<ErlangTokenId> getLanguage() {
        return language;
    }
    
}
