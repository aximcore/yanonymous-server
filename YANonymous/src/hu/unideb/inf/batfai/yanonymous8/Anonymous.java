/*
 * Anonymous.java
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

public class Anonymous extends LocalCommunityObject implements
        Unidentifiable.PoliticalAffinity {

	public boolean me = false;

    public Anonymous(String name, float x, float y) {

        this(false, name, x, y);

    }

    public Anonymous(float x, float y){
        this(false,"Andorid", x, y);
    }

    public Anonymous(boolean me, String name, float x, float y) {

    	super("felhasznalonev",name, x, y);

        this.me = me;

    }

    public Anonymous(String username, String name, float x, float y) {

        super(username,name, x, y);

        this.me = me;

    }

    public Anonymous(boolean me, float x, float y) {

        super("felhasznalonev","Android", x, y);

        this.me = me;

    }
    
    @Override
    public int getColor() {

        return PoliticalAffinity.partyColors.get(name);

    }

    public void setUserName(String username){
        this.username = username;
    }

    @Override
    public void next() {

        String pKey = null;
        String nName = null;

        for (String key : PoliticalAffinity.partyColors.keySet()) {

            if (name.equals(pKey)) {
                nName = key;
                break;
            }

            pKey = key;
        }

        if (nName == null) {
            name = PoliticalAffinity.partyColors.keySet().iterator().next();
        } else {
            name = nName;
        }
    }
}
