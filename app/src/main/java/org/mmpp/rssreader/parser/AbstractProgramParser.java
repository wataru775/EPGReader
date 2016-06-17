package org.mmpp.rssreader.parser;


/**
 * Created by wataru-n on 2016/06/16.
 */
public abstract class AbstractProgramParser<E extends AbstractProgramItem> {
    /**
     * 番組解析
     * @param source 解析対象ソース
     * @return 番組情報クラス
     */
    public java.util.List<Program> parse(String source){
        return ProgramConverter.convert(parseItems(source));
    }
    public abstract java.util.List<E> parseItems(String source);
}
