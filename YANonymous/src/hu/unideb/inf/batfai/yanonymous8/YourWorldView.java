/*
 * YourWorldView.java
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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.awt.*;
import java.io.IOException;

public class YourWorldView extends android.view.View {

	private static float gx = 0;
	private static float gy = 0;
	protected float width;
	protected float height;
	private static boolean first = true, newNode = false;
	protected float tx, ty;
	public static java.util.List<Anonymous> anonyms = new java.util.ArrayList<Anonymous>(); // ő tárolja az adatokat
	Anonymous selectedA = null;
	public static java.util.List<Relation> relations = new java.util.ArrayList<Relation>(); // kapcsolatok tárolására
	Relation selectedR = null;
	private static android.graphics.Paint bgrndPaint = new android.graphics.Paint();
	private static android.graphics.Paint pagerBgrndPaint = new android.graphics.Paint();
	private static android.graphics.Paint generalNodePaint = new android.graphics.Paint();
	private static android.graphics.Paint generalNodeBrdPaint = new android.graphics.Paint();
	private static android.graphics.Paint generalEdgePaint = new android.graphics.Paint();
	private static android.graphics.Paint textPaint = new android.graphics.Paint();
	private static android.graphics.Paint textBrdPaint = new android.graphics.Paint();
	private static int textSize = 44;
	private static android.graphics.Paint edgeTextPaint = new android.graphics.Paint();

    private Context context;

	public YourWorldView(android.content.Context context) {
		super(context);
        this.context = context;
		cinit();

	}

	public YourWorldView(android.content.Context context,
			android.util.AttributeSet attrs) {
		super(context, attrs);

		cinit();

	}

	public YourWorldView(android.content.Context context,
			android.util.AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		cinit();

	}

	private static android.graphics.drawable.Drawable logo;

	@Override
	protected void onSizeChanged(int newx, int newy, int x, int y) {

		super.onSizeChanged(newx, newy, x, y);

		width = newx;
		height = newy;

		if (first) {

			first = false;
			anonyms.add(new Anonymous(true, gx + width / 2, gy
					+ height / 2));

			int resId = getResources().getIdentifier("ic_launcher", "drawable",
					"hu.unideb.inf.batfai.yanonymous8");
			logo = getResources().getDrawable(resId);

		}
	}

	private void cinit() {

		pagerBgrndPaint.setColor(android.graphics.Color.rgb(0x77, 0xcc, 0xff));
		pagerBgrndPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
		pagerBgrndPaint.setAntiAlias(true);

		bgrndPaint.setColor(android.graphics.Color.rgb(0x33, 0x66, 0xff));
		bgrndPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
		bgrndPaint.setAntiAlias(true);

		generalNodePaint.setColor(android.graphics.Color.argb(0xb0, 0x00, 0x22,
				0xee));
		generalNodePaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
		generalNodePaint.setAntiAlias(true);

		generalNodeBrdPaint.setColor(android.graphics.Color.rgb(0xdd, 0xdd,
				0x00));
		generalNodeBrdPaint.setStyle(android.graphics.Paint.Style.STROKE);
		generalNodeBrdPaint.setAntiAlias(true);
		generalNodeBrdPaint.setStrokeWidth(3);

		generalEdgePaint.setColor(android.graphics.Color.argb(0xb0, 0x21, 0xaa,
				0xbf));
		generalEdgePaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
		generalEdgePaint.setAntiAlias(true);
		generalEdgePaint.setStrokeWidth(25);

		textPaint.setColor(android.graphics.Color.YELLOW);
		textPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
		textPaint.setAntiAlias(true);
		textPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
		textPaint.setTextSize(textSize);

		textBrdPaint.setColor(android.graphics.Color.rgb(0x41, 0x41, 0x41));
		textBrdPaint.setStyle(android.graphics.Paint.Style.STROKE);
		textBrdPaint.setAntiAlias(true);
		textBrdPaint.setStrokeWidth(1);
		textBrdPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
		textBrdPaint.setTextSize(textSize);

		edgeTextPaint.setColor(android.graphics.Color.argb(0xc0, 0x0e, 0x0d,
				0x0d));
		edgeTextPaint.setStyle(android.graphics.Paint.Style.FILL_AND_STROKE);
		edgeTextPaint.setAntiAlias(true);
		edgeTextPaint.setTextAlign(android.graphics.Paint.Align.CENTER);
		edgeTextPaint.setTextSize(textSize / 2);

	}

	public float d(float x1, float x2, float y1, float y2) {

		return (x1 - y1) * (x1 - y1) + (x2 - y2) * (x2 - y2);
	}

	private Anonymous nearestAnonymous(float x, float y) {

		Anonymous r = null;
		float max = /* Float.MAX_VALUE */4 * size * size, m;

		System.out.println("\n\n");

		for (Anonymous a : anonyms) {

			if ((m = d(a.x, a.y, x, y)) < max) {
				max = m;
				r = a;
				r.distexy = m;
			}
		}
		return r;
	}

	private Relation nearestRelation(float x, float y) {

		Relation r = null;
		float max = /* Float.MAX_VALUE */4 * size * size, m;

		for (Relation a : relations) {
			if ((m = d((a.nodeA.x + a.nodeB.x) / 2,
					(a.nodeA.y + a.nodeB.y) / 2, x, y)) < max) {
				max = m;
				r = a;
				r.distexy = m;
			}
		}
		return r;
	}

	protected boolean moved = false;
	protected float mx, my;
	protected float sgx, sgy;

    public float x,y;
    public Anonymous Temp;
    public View viewDialog;

	@Override
	public boolean onTouchEvent(android.view.MotionEvent event) {

		 x = event.getX();
		 y = event.getY();

		if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {

			selectedA = nearestAnonymous(gx + x, gy + y);
			selectedR = nearestRelation(gx + x, gy + y);

			if (selectedA != null && selectedR != null) {
				if (selectedA.distexy < selectedR.distexy) {
					selectedR = null;

				} else {

					selectedA = null;

				}

			}

		} else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE) {

			if (selectedA == null && selectedR == null) {

				if(!moved)
				{
					moved = true;
					mx = x;
					my = y;					
					sgx = gx;
					sgy = gy;
					
				} else {
				
				gx = sgx + x - mx;
				gy = sgy + y - my;
				}
			}

			if (selectedA != null) {
				newNode = true;
			} else {
				newNode = false;
			}

			tx = x;
			ty = y;

		} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {

			if (newNode && selectedA != null) {

				if (x <= 5 || x >= width - 5 || y <= 5 || y >= height - 5) {

					// if (anonyms.size() > 1) {
					if (!selectedA.me) {

						java.util.List<Relation> toRemoveAll = new java.util.ArrayList<Relation>();
						for (Relation r : relations)
							if (r.nodeA == selectedA || r.nodeB == selectedA)
								toRemoveAll.add(r);

						relations.removeAll(toRemoveAll);

						anonyms.remove(selectedA);

					}

				} else {                                                        // ha elhúztam a megfelelőhelyre

					if (d(selectedA.x, selectedA.y, gx + x, gy + y) > 4 * size  // create pont
							* size) {


                        Temp = new Anonymous("Android",gx + x, gy + y);

                        // dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(super.getContext());

                        LayoutInflater inflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        viewDialog = inflater.inflate(R.layout.user,null);

                        builder.setView(viewDialog)
                                .setTitle("Ismerős adatai")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    EditText editText = (EditText) viewDialog.findViewById(R.id.editText);
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        String username = editText.getText().toString();
                                        Temp.setUserName(username);
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int id){

                                        //LoginDialogFragment.this.getDialog().cancel();
                                    }
                                });
                        builder.create().show();

                        //Toast.makeText(super.getContext(), "Következő hiba lépet fel ", Toast.LENGTH_SHORT).show();

                        anonyms.add(Temp);
                        relations.add(new Relation(selectedA, Temp));


					}
				}

			} else {

				if (selectedR != null) {

					selectedR.next();

				} else if (selectedA != null) {

					selectedA.next();

				} /*else {

					if (moved) {

						gx += x - mx;
						gy += y - my;

					}

				}*/

			}

			newNode = false;
			moved = false;

		}

		invalidate();

		return true;
	}

	android.graphics.Rect boundsRect = new android.graphics.Rect();
	int size;
	android.graphics.Path edgePath = new android.graphics.Path();

	@Override
	protected void onDraw(android.graphics.Canvas canvas) {

		canvas.drawRect(0, 0, width, height, bgrndPaint);

		generalEdgePaint.setStrokeWidth(25);
		for (Relation r : relations) {

			generalEdgePaint.setColor(r.getColor());
			canvas.drawLine(-gx + r.nodeA.x, -gy + r.nodeA.y, -gx + r.nodeB.x,
					-gy + r.nodeB.y, generalEdgePaint);

			edgePath.reset();
			edgePath.moveTo(-gx + r.nodeA.x, -gy + r.nodeA.y);
			edgePath.lineTo(-gx + r.nodeB.x, -gy + r.nodeB.y);

			canvas.drawTextOnPath(r.name, edgePath, 6, 6, edgeTextPaint);

		}

		if (selectedA != null && newNode) {
			canvas.drawLine(-gx + selectedA.x, -gy + selectedA.y, tx, ty,
					generalEdgePaint);
		}

		for (Anonymous a : anonyms) {

			textPaint.getTextBounds(a.name, 0, a.name.length(), boundsRect);
			size = boundsRect.width() / 2 + 10;

			generalNodePaint.setColor(a.getColor());
			canvas.drawCircle(-gx + a.x, -gy + a.y, size, generalNodePaint);
			canvas.drawCircle(-gx + a.x, -gy + a.y, size, generalNodeBrdPaint);

			if (a.me) {

				logo.setBounds((int) (-gx + a.x), (int) (-gy + a.y - 100 / 2),
						(int) (-gx + a.x + 100 / 2), (int) (-gy + a.y));
				logo.draw(canvas);
			}

			canvas.drawText(a.name, -gx + a.x,
					-gy + a.y
							- ((textPaint.descent() + textPaint.ascent()) / 2),
					textPaint);
			canvas.drawText(a.name, -gx + a.x,                                      // mit választott kiírása
					-gy + a.y
							- ((textPaint.descent() + textPaint.ascent()) / 2),
					textBrdPaint);

            canvas.drawText(a.username, -gx + a.x,                                  // felhasználónév
                    -gy + a.y
                            - ((textPaint.descent() + textPaint.ascent()) / 2) + 40,
                    textBrdPaint);
		}
		
		canvas.clipRect(width - width/4, height - height/6, width-5, height-5);
		canvas.drawRect(width - width/4, height - height/6, width-5, height-5, pagerBgrndPaint);
		
		generalEdgePaint.setStrokeWidth(4);
		for (Relation r : relations) {

			generalEdgePaint.setColor(r.getColor());
			canvas.drawLine(
					width - width/4 + 
					(-gx + r.nodeA.x)/10, 
					height - height/6 + 
					(-gy + r.nodeA.y)/10,
					width - width/4 +
					(-gx + r.nodeB.x)/10,
					height - height/6 + 
					(-gy + r.nodeB.y)/10, 
					generalEdgePaint);
		}

		for (Anonymous a : anonyms) {

			generalNodePaint.setColor(a.getColor());
			canvas.drawCircle(
					width - width/4 +
					(-gx + a.x)/10, 
					height - height/6 + 
					(-gy + a.y)/10, 
					10, generalNodePaint);
			canvas.drawCircle(-gx + a.x, -gy + a.y, 10, generalNodeBrdPaint);

		}		

		canvas.drawRect(width - width/4, height - height/6, width-5, height-5, generalNodeBrdPaint);
		
	}
}
