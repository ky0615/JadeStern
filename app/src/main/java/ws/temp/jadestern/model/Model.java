package ws.temp.jadestern.model;

import android.content.Context;
import io.realm.Realm;

public class Model {
    protected static Context context;

    protected static Realm realm;

    public Model() {

    }

    public Model(Context context) {
        Model.context = context;
        Model.realm = Realm.getInstance(context);
    }
}
