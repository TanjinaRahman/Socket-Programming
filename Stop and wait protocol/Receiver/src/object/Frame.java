/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author Aspire
 */
public class Frame implements Serializable {

    private int seq;
    private String data;
    private int ack;
    private String tailer;

    public Frame(String data) {
        this.data = data;
    }

    public Frame(int seq, String data, int ack, String trailer) {
        this.seq = seq;
        this.data = data;
        this.ack = ack;
        this.tailer = trailer;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getAck() {
        return ack;
    }

    public void setAck(int ack) {
        this.ack = ack;
    }

    public String getTailer() {
        return tailer;
    }

    public void setTailer(String tailer) {
        this.tailer = tailer;
    }
}

