package com.a.aykut.tryazuremobileservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.microsoft.windowsazure.notifications.NotificationsManager;

public class MainActivity extends AppCompatActivity {
  TextView mDurum;

  public class Item {
    public String Id;
    public String Text;
  }


  public class Personel {
    public String id;
    public String prsAd;
    public String prsSoyad;
    public String prsEmail;
  }

  public class Isletme {
    public String id;
    public String isletme_Ad;
    public int isletme_CalisanSayi;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mDurum = (TextView) findViewById(R.id.txt_Durum);

    // Aşağıdaki fonk. çağırarak Uygulamamızı Azure Notification yapısına kaydediyoruz.
    // Sorunsuz çalıştığı takdirde uygulamamız push notification alabilir durumda olacaktır.
    // Bir push bildirimi geldiğinden MyHandler sınıfımız çalışacaktır.
    NotificationsManager.handleNotifications(this, Const.SENDER_ID, MyHandler.class);

    Item item = new Item();
    item.Text = "ItemText";


    Personel prs = new Personel();
    prs.prsAd = "Aykut";
    prs.prsSoyad = "Asil";
    prs.prsEmail = "aykuttasil@hotmail.com";


    try {

      // Aşağıdaki yapıyı kullanarak Azure da oluşturduğumuz tablolara veri ekliyoruz.
      // Örneğin Item sınıfından yeni bir instance oluşturup bunu gönderiyoruz.
      // Sınıfa herhangi yeni bir alan eklendiğinde Azure daki tablomuz otomatik olarak güncellenecektir.
      // Bu durum Auto Schema özelliği açık olduğundan çalışmaktadır.
      // Production ortamına taşındığında bu özelliğin kapatılması önerilir.
      AzureMobileServiceTableHelper amsth = new AzureMobileServiceTableHelper(MainActivity.this);
      amsth.insertTable(Item.class, item);
      amsth.insertTable(Item.class, item);
      amsth.insertTable(Item.class, item);


      //Personel tablosuna eklemek yapılıyor.
      amsth.insertTable(Personel.class, prs);
      prs.prsAd = "Kamil";
      amsth.insertTable(Personel.class, prs);

    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
