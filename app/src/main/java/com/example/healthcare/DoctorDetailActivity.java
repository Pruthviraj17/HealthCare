package com.example.healthcare;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {

    private String[][] doctordetails1={
            {"Doctor Name: Ajit Barkale","Hospital Address: Kolhapur", "EXP: 5yrs", "Mobile No: 9923098756", "600"},
            {"Doctor Name: Nilesh Pawar","Hospital Address: Hupri", "EXP: 15yrs", "Mobile No: 9134765477", "900"},
            {"Doctor Name: Swapnil Kale","Hospital Address: Pune", "EXP: 8yrs", "Mobile No: 9834521388", "300"},
            {"Doctor Name: Deepak Deshmuk","Hospital Address: Chinchwad", "EXP: 6yrs", "Mobile No: 9877542314", "500"},
            {"Doctor Name: Ashok Pande","Hospital Address: Katraj", "EXP: 7yrs", "Mobile No: 9923098756", "800"}
    };
    private String[][] doctordetails2={
            {"Doctor Name: Ramesh Patil","Hospital Address: Nigdi", "EXP: 5yrs", "Mobile No: 9923098756", "600"},
            {"Doctor Name: Neelam Patil","Hospital Address: Pimpri", "EXP: 15yrs", "Mobile No: 9134765477", "900"},
            {"Doctor Name: Swati Pawar","Hospital Address: Pune", "EXP: 8yrs", "Mobile No: 9834521388", "300"},
            {"Doctor Name: Mayuri Deshmuk","Hospital Address: Chinchwad", "EXP: 6yrs", "Mobile No: 9877542314", "500"},
            {"Doctor Name: Niraj Kale","Hospital Address: Katraj", "EXP: 7yrs", "Mobile No: 9923098756", "800"}
    };
    private String[][] doctordetails3={
            {"Doctor Name: Seema Patil","Hospital Address: Kolhapur", "EXP: 5yrs", "Mobile No: 9923098756", "600"},
            {"Doctor Name: Pankaj Parab","Hospital Address: Hupri", "EXP: 15yrs", "Mobile No: 9134765477", "900"},
            {"Doctor Name: Monish Jain","Hospital Address: Pune", "EXP: 8yrs", "Mobile No: 9834521388", "300"},
            {"Doctor Name: Vishal Deshmuk","Hospital Address: Chinchwad", "EXP: 6yrs", "Mobile No: 9877542314", "500"},
            {"Doctor Name: Shrikant Pande","Hospital Address: Katraj", "EXP: 7yrs", "Mobile No: 9923098756", "800"}
    };
    private String[][] doctordetails4={
            {"Doctor Name: Amol Gavade","Hospital Address: Kolhapur", "EXP: 5yrs", "Mobile No: 9923098756", "600"},
            {"Doctor Name: Prasad Pawar","Hospital Address: Hupri", "EXP: 15yrs", "Mobile No: 9134765477", "900"},
            {"Doctor Name: Deepak Kale","Hospital Address: Pune", "EXP: 8yrs", "Mobile No: 9834521388", "300"},
            {"Doctor Name: Nilesh Deshmuk","Hospital Address: Chinchwad", "EXP: 6yrs", "Mobile No: 9877542314", "500"},
            {"Doctor Name: Nilesh Despande","Hospital Address: Katraj", "EXP: 7yrs", "Mobile No: 9923098756", "800"}
    };
    private String[][] doctordetails5={
            {"Doctor Name: Nilesh Borate","Hospital Address: Kolhapur", "EXP: 5yrs", "Mobile No: 9923098756", "600"},
            {"Doctor Name: Pankaj Pawar","Hospital Address: Hupri", "EXP: 15yrs", "Mobile No: 9134765477", "900"},
            {"Doctor Name: Swapnil Lile","Hospital Address: Pune", "EXP: 8yrs", "Mobile No: 9834521388", "300"},
            {"Doctor Name: Deepak Pawar","Hospital Address: Chinchwad", "EXP: 6yrs", "Mobile No: 9877542314", "500"},
            {"Doctor Name: Ashok Singh","Hospital Address: Katraj", "EXP: 7yrs", "Mobile No: 9923098756", "800"}
    };
    private Button btn;
    private TextView tv;
    String[][] doctor_detail={};

    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        btn=findViewById(R.id.DDbutton);
        tv=findViewById(R.id.textViewHADTitle);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_detail=doctordetails1;
        else if(title.compareTo("Dietician")==0)
            doctor_detail=doctordetails2;
        else if(title.compareTo("Dentist")==0)
            doctor_detail=doctordetails3;
        else if(title.compareTo("Surgeon")==0)
            doctor_detail=doctordetails4;
        else
            doctor_detail=doctordetails5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailActivity.this,FindDoctorActivity.class));
            }
        });

        list =new ArrayList();
        for (String[] strings : doctor_detail) {
            item = new HashMap<String, String>();
            item.put("line1", strings[0]);
            item.put("line2", strings[1]);
            item.put("line3", strings[2]);
            item.put("line4", strings[3]);
            item.put("line5", "Cons fee: " + strings[4] + "/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst=findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_detail[i][0]);
                it.putExtra("text3",doctor_detail[i][1]);
                it.putExtra("text4",doctor_detail[i][3]);
                it.putExtra("text5",doctor_detail[i][4]);
                startActivity(it);
            }
        });

        }
}