/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lord.disease.erlanglexer.lexer;

/**
 *
 * @author alain
 */
public enum TokenType {

    T__0(1, "character"),
    T__1(2, "character"),
    T__2(3, "character"),
    T__3(4, "character"),
    T__4(5, "character"),
    T__5(6, "character"),
    T__6(7, "character"),
    T__7(8, "character"),
    T__8(9, "character"),
    T__9(10, "character"),
    T__10(11, "character"),
    T__11(12, "character"),
    T__12(13, "keyword"),
    T__13(14, "character"),
    T__14(15, "character"),
    T__15(16, "character"),
    T__16(17, "character"),
    T__17(18, "character"),
    T__18(19, "character"),
    T__19(20, "keyword"),
    T__20(21, "operator"),
    T__21(22, "operator"),
    T__22(23, "character"),
    T__23(24, "keyword"),
    T__24(25, "operator"),
    T__25(26, "operator"),
    T__26(27, "keyword"),
    T__27(28, "keyword"),
    T__28(29, "keyword"),
    T__29(30, "keyword"),
    T__30(31, "operator"),
    T__31(32, "operator"),
    T__32(33, "operator"),
    T__33(34, "keyword"),
    T__34(35, "keyword"),
    T__35(36, "keyword"),
    T__36(37, "keyword"),
    T__37(38, "keyword"),
    T__38(39, "keyword"),
    T__39(40, "operator"),
    T__40(41, "keyword"),
    T__41(42, "keyword"),
    T__42(43, "keyword"),
    T__43(44, "keyword"),
    T__44(45, "keyword"),
    T__45(46, "keyword"),
    T__46(47, "keyword"),
    T__47(48, "keyword"),
    T__48(49, "keyword"),
    T__49(50, "keyword"),
    T__50(51, "keyword"),
    T__51(52, "keyword"),
    T__52(53, "operator"),
    T__53(54, "operator"),
    T__54(55, "operator"),
    T__55(56, "operator"),
    T__56(57, "operator"),
    T__57(58, "operator"),
    T__58(59, "operator"),
    T__59(60, "operator"),
    T__60(61, "operator"),
    T__61(62, "operator"),
    T__62(63, "operator"),
    TokAtom(64, "literal"),
    TokVar(65, "identifier"),
    TokFloat(66, "number"),
    TokInteger(67, "number"),
    TokChar(68, "character"),
    TokString(69, "string"),
    AttrName(70, "literal"),
    Comment(71, "comment"),
    WS(72, "whitespace"),
    Macro(73, "literal"),
    ERR_CHAR(74, "error")
    ;
    public int id;
    public String category;
    public String text;

    private TokenType(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public static TokenType valueOf(int id) {
        TokenType[] values = values();
        for (TokenType value : values) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("The id " + id + " is not recognized");
    }

}
