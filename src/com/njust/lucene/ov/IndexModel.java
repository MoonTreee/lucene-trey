package com.njust.lucene.ov;


/**
 * lucene模型,用于在页面展示
 * @author trey
 *
 */
public class IndexModel {
    private String id;
    private String title;
    private String key_word;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }
}
