package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class MainActor extends AbstractActor {

    Matriz matriz;
    int tamResultado;
    Matriz resultado;

    public enum Messages{
        ENVIAR_MATRIZ
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(Matriz.class, m -> matriz = m)
                .match(Messages.ENVIAR_MATRIZ.getClass(), m -> sender().tell(matriz, self()))
                .match(TaskCalcElement.class, t -> {

                    resultado.getValues()[t.getRowIndex()][t.getCloIndex()] = t.getElement();
                    tamResultado--;

                    if(tamResultado == 0){
                        System.out.println(resultado.toString());
                    }

                }).match(int[][].class, m -> {

                    resultado = new Matriz(m);
                    tamResultado = m.length * m[0].length;

                }).build();
    }

    public static Props props(){
        return Props.create(MainActor.class);
    }

    public static void main(String[] args) {
        int[][] mat1Values = {
                {1, 2},
                {3, 4},
                {5, 6},
        };
        Matriz m1 = new Matriz(mat1Values);
        int[][] mat2Values = {
                {1, 2, 3},
                {3, 4, 5}
        };
        Matriz m2 = new Matriz(mat2Values);

        ActorSystem actorSystem = ActorSystem.create("system");
        ActorRef mainActor = actorSystem.actorOf(MainActor.props(), "MainActor");
        ActorRef mulActor = actorSystem.actorOf(MulMatriz.props(), "MulActor");
        mainActor.tell(m2, ActorRef.noSender());
        mulActor.tell(m1, mainActor);
        actorSystem.terminate();
        System.exit(0);
    }
}
