package objectpass;

import java.io.Serializable;

public class Frame implements Serializable {

    private int seq;
    private String data;
    private String ack;
    private String tailer;

    public Frame(String data) {
        this.data = data;
    }

    public Frame(int seq, String data, String ack, String trailer) {
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

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getTailer() {
        return tailer;
    }

    public void setTailer(String tailer) {
        this.tailer = tailer;
    }
}