package w33;

//攻取西川参谋部，就是上下文啦，由上下文来选择具体的策略
public class OccupationContext  {

    public void occupationWestOfSichuan(String msg){
        //先用上上计策
        IOccupationStrategyWestOfSiChuan strategy = new UpperStrategy();
        try {
            strategy.occupationWestOfSiChuan(msg);
        } catch (Exception e) {
            //上上计策有问题行不通之后，用中计策
            strategy = new MiddleStrategy();
            strategy.occupationWestOfSiChuan(msg);
        }
    }
}
