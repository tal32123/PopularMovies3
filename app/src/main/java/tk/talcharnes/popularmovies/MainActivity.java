package tk.talcharnes.popularmovies;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import tk.talcharnes.popularmovies.db.FavoriteMovie;
import tk.talcharnes.popularmovies.service.PosterService;

public class MainActivity extends AppCompatActivity implements PostersFragment.Callback{
        private Bundle state;
    Bundle args;
    private boolean mTwoPane;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    @Override
    public void onItemSelected(String sortUri, String position) {
        if(mTwoPane){
            args = new Bundle();

            args.putString("position", position);
            args.putString("uri", sortUri.toString());

            MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
            movieDetailsFragment.setArguments(args);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, movieDetailsFragment, DETAILFRAGMENT_TAG)
                    .commit();
        }
        else{
            Intent intent = new Intent(this, MovieDetails.class);
            intent.putExtra("position", (""+position));
            intent.putExtra("uri", sortUri.toString());
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        state = savedInstanceState;

        setContentView(R.layout.activity_main);


  //  Fragment fragment = getSupportFragmentManager().findFragmentByTag("FRAGMENT");
   if (savedInstanceState == null) {
    //    if (fragment == null) {
//            FetchPostersTask fetchPostersTask = new FetchPostersTask(getApplicationContext());
//            fetchPostersTask.execute();
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment, new PostersFragment(), "FRAGMENT")
//                    .commit();

        Intent alarmIntent = new Intent(this, PosterService.AlarmReceiver.class);
       PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
       AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
       alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);

       Intent intent = new Intent(this, PosterService.class);
       Log.i(this.getClass().getSimpleName(), "starting poster service");
       this.startService(intent);
        }
if(findViewById(R.id.movie_detail_container)!=null){
    mTwoPane = true;
    if (savedInstanceState == null) {
        // The detail container view will be present only in the large-screen layouts
        // (res/layout-sw600dp). If this view is present, then the activity should be
        // in two-pane mode.

                        getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.movie_detail_container, new MovieDetailsFragment(), DETAILFRAGMENT_TAG)
                                        .commit();
}
    }
else{
    mTwoPane = false;
        }

//    }

        //Checks to see if there is internet connection or not. If so, it brings you into the proper layout.
        //Otherwise it brings you to a layout stating that a connection is necessary to continue (and it has a refresh button)
//        if(savedInstanceState==null){ //&& isNetworkAvailable()){
//        setContentView(R.layout.activity_main);
//
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment, new PostersFragment())
//                    .commit();
//        }
//        else {
//            setContentView(R.layout.no_network);
//
////        }
//        else{
//
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment, fragment)
//                    .commit();
//
//        }
    }
    //refresh button for once a connection is established
//    public void refresh(View view){
//        if(state==null && isNetworkAvailable()){
//            setContentView(R.layout.activity_main);
//
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment, new PostersFragment())
//                    .commit();
//        }    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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


        return super.onOptionsItemSelected(item);
    }
    public void favorited(View v){
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.favorited(v, args, getApplicationContext());
    }
}
