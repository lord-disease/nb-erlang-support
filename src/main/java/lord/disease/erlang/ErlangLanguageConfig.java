/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lord.disease.erlang;

import lord.disease.erlanglexer.lexer.ErlangTokenId;
import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;

/**
 *
 * @author alain
 */
@LanguageRegistration(mimeType = "text/x-erlang")
public class ErlangLanguageConfig extends DefaultLanguageConfig{

    @Override
    public String getDisplayName() {
        return "Erlang";
    }

    @Override
    public Language getLexerLanguage() {
        return ErlangTokenId.getLanguage();
    }
    
}
