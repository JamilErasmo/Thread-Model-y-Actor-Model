package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class MulMatriz extends AbstractActor {
    Matriz m1;
    Matriz m2;
    boolean isSend = false;

    public static Props props(){
        return Props.create(MulMatriz.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Matriz.class, matriz -> {

                    if (!isSend) {
                        m1 = matriz;
                        sender().tell(MainActor.Messages.ENVIAR_MATRIZ, self());
                        isSend = true;
                        return;
                    }else {
                        m2 = matriz;
                    }

                    int tM1 = this.m1.getValues().length;
                    int tM2 = this.m2.getValues()[0].length;

                    if (tM1 == tM2) {

                        int [][] output = new int[tM1][tM2];
                        sender().tell(output, self());

                        ActorSystem actorSystem = ActorSystem.create();
                        for (var i = 0; i < output.length; i++) {

                            for (var j = 0; j < output[0].length; j++) {
                                ActorRef mulActor = actorSystem.actorOf(MulFilaColumna.props(), "Actor_"+i+"_"+j);
                                mulActor.tell(new TaskCalcElement(m1, m2, i, j), sender());
                            }
                        }
                    }

                }).build();
    }
}
