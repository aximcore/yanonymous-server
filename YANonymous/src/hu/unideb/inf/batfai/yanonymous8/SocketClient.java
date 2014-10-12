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
public class SocketClient implements Runnable {
    List<Anonymous> a; List<Relation> b; int i = 0;

    public SocketClient( List<Anonymous> inData, List<Relation> relation ) {
        b = relation;
        a = inData;
    }

    private Person readData(Anonymous a){

        Person.Builder person = Person.newBuilder();

        if ( a.name == "Android")
            person.setChosen(Person.Chosen.Android);
        else if ( a.name == "iOS")
            person.setChosen(Person.Chosen.iOS);
        else if ( a.name == "Windows")
            person.setChosen(Person.Chosen.Windows);
        else
            person.setChosen(Person.Chosen.Others);

        person.setUsername(a.username);
        person.setFriend(b.get(i).nodeB.username);
        i++;

        return person.build();
    }

    public void run(){
            Connect();
    }

    public void Connect() {
        try {
            Socket client = new Socket("192.168.1.100", 9090);
            Datas.Builder datas = Datas.newBuilder();

            for (Anonymous i : a)
                datas.addPerson(readData(i));

            datas.build().writeTo(client.getOutputStream());
            datas.clear();
            client.close();

        }catch (IOException io){

        }
        finally {

        }
    } // connect
}
