package hu.unideb.inf.batfai.yanonymous8;

import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import hu.unideb.inf.batfai.yanonymous8.YanoProto.Datas;
import hu.unideb.inf.batfai.yanonymous8.YanoProto.Person;
import hu.unideb.inf.batfai.yanonymous8.YanoProto.Datas.Builder;
/**
 * Created by aximcore on 2014.10.04..
 */
public class SocketClient extends android.view.View implements Runnable  {
    List<Anonymous> a = YourWorldView.anonyms;;
    List<Relation> b = YourWorldView.relations;
    int i = 0;

    public SocketClient(android.content.Context context){
        super(context);
        //Toast.makeText(getContext(), /*b.get(i).nodeB.username*/ b.get(2).getKapcs(), Toast.LENGTH_SHORT).show();
    }

    private Person readData(Anonymous any){

        Person.Builder person = Person.newBuilder();

        if ( any.name == "Android")
            person.setChosen(Person.Chosen.Android);
        else if ( any.name == "iOS")
            person.setChosen(Person.Chosen.iOS);
        else if ( any.name == "Windows")
            person.setChosen(Person.Chosen.Windows);
        else
            person.setChosen(Person.Chosen.Others);

        person.setUsername(any.username);

        if ( i < a.size()-1 ) {
            person.setMyFriend(b.get(i).getFriend());

            if ( b.get(i).getKapcs() == "Relationship")
                person.setRelation(Person.Relation.Relationship);
            else if ( b.get(i).getKapcs() == "Csajom")
                person.setRelation(Person.Relation.Girlfriend);
            else if ( b.get(i).getKapcs() == "Fiúm")
                person.setRelation(Person.Relation.Boyfriend);
            else if (b.get(i).getKapcs() == "Muter")
                person.setRelation(Person.Relation.Mother);
            else if (b.get(i).getKapcs() == "Fater")
                person.setRelation(Person.Relation.Father);
            else if (b.get(i).getKapcs() == "Tesó")
                person.setRelation(Person.Relation.Testver);
            else if (b.get(i).getKapcs() == "Szomszéd")
                person.setRelation(Person.Relation.Neighbor);



            i++;
        }


        //if ( b.size() > i)
          //  i++;

        return person.build();
    }

    public void run(){
            Connect();
    }

    public void Connect() {
        try {
            Socket client = new Socket("192.168.1.103", 9090);
            Datas.Builder datas = Datas.newBuilder();

            for (int i = 0; i < a.size();i++)
                datas.addPerson(readData(a.get(i)));

            datas.build().writeTo(client.getOutputStream());
            datas.clear();
            client.close();

        }catch (IOException io){

        }
        finally {

        }
    } // connect
}
