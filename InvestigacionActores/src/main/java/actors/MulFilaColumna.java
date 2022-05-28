package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class MulFilaColumna extends AbstractActor {

    public static Props props(){
        return Props.create(MulFilaColumna.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TaskCalcElement.class, task -> {
                    task.calcValue();
                    sender().tell(task, self());
                }).build();
    }
}
