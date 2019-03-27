package w33;

//此时外部客户端相当于刘备了,不管具体采用什么计策，只要结果（成功的攻下西川）
public class Client {

    public static void main(String[] args) {
        OccupationContext context = new  OccupationContext();
        //这个给手下的人激励不够啊
        context.occupationWestOfSichuan("拿下西川");
        System.out.println("=========================");
        //这个人人有赏，让士兵有动力啊
        context.occupationWestOfSichuan("拿下西川之后，人人有赏！");
    }
}
