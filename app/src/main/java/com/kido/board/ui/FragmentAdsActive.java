package com.kido.board.ui;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kido.board.R;
import com.kido.board.adapters.AdsUserAdapter;
import com.kido.board.model.Ad;
import com.kido.board.model.serverResponse.ServerAds;
import com.kido.board.network.GsonRequest;
import com.kido.board.network.VolleySingleton;
import com.kido.board.util.CustomSwype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FragmentAdsActive extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static String API_GET_ACTIVE_ADS = "/AdsActive.php";
    private static List<Ad> ads;
    private Activity mActivity;
    private Context fContext;
    private View rootView;
    private RecyclerView recyclerView;
    private CardView cardView;
    private RecyclerView.Adapter mAdapter;
    private CustomSwype mSwipeRefreshLayout;
    private String TAG = FragmentAdsActive.class.toString();
    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;
    private boolean isTaskRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ads_active, container, false);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        fContext = getActivity().getApplicationContext();
        mActivity = (MainActivity) getActivity();

        if (ads == null) {
            ads = new ArrayList<Ad>();
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(fContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        mAdapter = new AdsUserAdapter(this.getFragmentManager(), ads);
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (CustomSwype) rootView.findViewById(R.id.id_swype);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(255, 155, 30));
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.LTGRAY);

        if (isTaskRunning) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
        if (savedInstanceState == null) {
            getAdFromServer();
        } else {
            Gson gson = new Gson();
            String s = savedInstanceState.getString("ads");
            Ad[] obj = gson.fromJson(s, Ad[].class);
            if (obj == null) {
                ads = new ArrayList<Ad>();
            } else {
                ads = new ArrayList<Ad>(Arrays.asList(obj));
            }
            mAdapter.notifyDataSetChanged();
        }

    }

    public void getAdFromServer() {
        if (!isTaskRunning) {
            isTaskRunning = true;
            mSwipeRefreshLayout.setRefreshing(true);
            Log.d(TAG, "Query: getAdFromServer: " + Long.toString(System.currentTimeMillis()));
            VolleySingleton.getInstance(fContext).addToRequestQueue(
                    new GsonRequest<ServerAds>(Request.Method.GET,
                            API_GET_ACTIVE_ADS,
                            ServerAds.class,
                            null,
                            new Response.Listener<ServerAds>() {
                                @Override
                                public void onResponse(ServerAds response) {
                                    isTaskRunning = false;
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    ads.clear();
                                    if (response.getValue()!=null){
                                        ads.addAll(response.getValue());
                                    }
                                    mAdapter.notifyDataSetChanged();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    isTaskRunning = false;
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    Log.e(TAG, "err: " + error.toString());
                                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                        Toast.makeText(fContext, "TimeOut Error", Toast.LENGTH_LONG).show();
                                    } else if (error instanceof AuthFailureError) {
                                        Toast.makeText(fContext, "AuthFailureError", Toast.LENGTH_LONG).show();
                                    } else if (error instanceof ServerError) {
                                        Toast.makeText(fContext, "ServerError", Toast.LENGTH_LONG).show();
                                    } else if (error instanceof NetworkError) {
                                        Toast.makeText(fContext, "NetworkError", Toast.LENGTH_LONG).show();
                                    } else if (error instanceof ParseError) {
                                        Toast.makeText(fContext, "ParseError", Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            null), TAG);
        }
    }

    @Override
    public void onRefresh() {
        getAdFromServer();
    }

    @Override
    public void onDetach() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isShown()) {
            mSwipeRefreshLayout.setRefreshing(false);
            VolleySingleton.getInstance(fContext).cancelPendingRequests(TAG);
        }
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        String s = gson.toJson(ads);
        outState.putString("ads", s);
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Gson gson = new Gson();
            String s = savedInstanceState.getString("ads");
            Ad[] obj = gson.fromJson(s, Ad[].class);
            if (obj == null) {
                ads = new ArrayList<Ad>();
            } else {
                ads = new ArrayList<Ad>(Arrays.asList(obj));
            }
        }
        super.onViewStateRestored(savedInstanceState);
    }
}
