package ZUniverse;

class RCTransaction {
    static int id;
    int t_id;
    String senderName;
    String receiverName;
    String sender_ZID;
    String receiver_ZID;
    int amount;

    RCTransaction(String senderName, String receiverName,String sender_ZID, String receiver_ZID, int amount) {
        this.t_id = ++id;
	this.senderName = senderName;
	this.receiverName = receiverName;
	this.sender_ZID = sender_ZID;
	this.receiver_ZID = receiver_ZID;
	this.amount = amount;
    }
}