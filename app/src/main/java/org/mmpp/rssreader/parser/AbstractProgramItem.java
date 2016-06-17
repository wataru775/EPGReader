package org.mmpp.rssreader.parser;

/**
 * 番組情報の抽象クラス
 * Created by wataru-n on 2016/06/17.
 */
public abstract class AbstractProgramItem {

    /**
     * 番組名
     */
    public String name;

    /**
     * 開始時間
     */
    public String start;

    /**
     * 放送局
     */
    public String channel;

    /**
     * 番組情報に変換する
     * @return 番組情報
     */
    public abstract Program convert();
}
