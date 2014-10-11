/*
 * YanonymousActivity.java
 *
 * YANonymous/5-prog2
 * http://progpater.blog.hu/2013/09/17/o_mondd_te_kit_valasztanal_525
 *
 * Copyright (C) 2010, Dr. Bátfai Norbert
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Ez a program szabad szoftver; terjeszthető illetve módosítható a
 * Free Software Foundation által kiadott GNU General Public License
 * dokumentumában leírtak; akár a licenc 3-as, akár (tetszőleges) későbbi
 * változata szerint.
 *
 * Ez a program abban a reményben kerül közreadásra, hogy hasznos lesz,
 * de minden egyéb GARANCIA NÉLKÜL, az ELADHATÓSÁGRA vagy VALAMELY CÉLRA
 * VALÓ ALKALMAZHATÓSÁGRA való származtatott garanciát is beleértve.
 * További részleteket a GNU General Public License tartalmaz.
 *
 * A felhasználónak a programmal együtt meg kell kapnia a GNU General
 * Public License egy példányát; ha mégsem kapta meg, akkor
 * tekintse meg a <http://www.gnu.org/licenses/> oldalon.
 *
 * 
 *
 * Version history:
 *
 * 0.0.1, 2013.szept.26., az Eclipse projekt átírása IDE független Maven projektbe.
 */
package hu.unideb.inf.batfai.yanonymous8;

import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class YanonymousActivity extends android.app.Activity
{
    @Override
    public void onCreate(android.os.Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    //-----------------------------------------------------------------------------------------------------------

    public void SendData(View v){
        new Thread(new SocketClient(YourWorldView.anonyms)).start();
    }

    public void start(List<Anonymous> a){
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            Socket client = new Socket("192.168.1.100", 9090);

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            //for (Anonymous i : a) {
            // és egy azonosító  megírása
            out.println(a.get(0).name);
            //out.println(a.get(0).x);
            //out.println(a.get(0).y);

                //System.out.println(i.name);
            //}
        } catch (IOException io){
            WriteError(io.toString());
        }
    }

    public void WriteError(String Error){
        Toast.makeText(this, "Következő hiba lépet fel " + Error, Toast.LENGTH_SHORT).show();
    };

    //-----------------------------------------------------------------------------------------------------------
}
