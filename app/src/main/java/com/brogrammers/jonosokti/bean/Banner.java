package com.brogrammers.jonosokti.bean;

import java.io.Serializable;

public class Banner implements Serializable {
    String link, documentId;

    public Banner() {
    }

    public Banner(String link, String documentId) {
        this.link = link;
        this.documentId = documentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
