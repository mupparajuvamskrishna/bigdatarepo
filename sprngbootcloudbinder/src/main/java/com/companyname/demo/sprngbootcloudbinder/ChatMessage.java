package com.companyname.demo.sprngbootcloudbinder;
public class ChatMessage {
    private  String contents;
    private  long time;

    public ChatMessage(String contents, long time) {
        this.contents = contents;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getContents() {
        return contents;
    }
}