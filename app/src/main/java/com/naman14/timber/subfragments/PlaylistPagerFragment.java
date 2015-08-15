package com.naman14.timber.subfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naman14.timber.R;
import com.naman14.timber.dataloaders.PlaylistLoader;
import com.naman14.timber.models.Playlist;

import java.util.List;
import java.util.Random;

/**
 * Created by naman on 14/08/15.
 */
public class PlaylistPagerFragment extends Fragment {

    private static final String ARG_PAGE_NUMBER = "pageNumber";

    int[] foregroundColors = {R.color.pink_transparent, R.color.green_transparent,R.color.blue_transparent,R.color.red_transparent,R.color.purple_transparent};

    public static PlaylistPagerFragment newInstance(int pageNumber) {
        PlaylistPagerFragment fragment = new PlaylistPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE_NUMBER, pageNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_playlist_pager, container, false);

        final List<Playlist> playlists= PlaylistLoader.getPlaylists(getActivity());
        Playlist playlist=playlists.get(getArguments().getInt(ARG_PAGE_NUMBER));

        TextView playlistame=(TextView) rootView.findViewById(R.id.name);
        TextView playlistnumber=(TextView) rootView.findViewById(R.id.number);
        ImageView playlistImage =(ImageView) rootView.findViewById(R.id.playlist_image);
        View foreground =rootView.findViewById(R.id.foreground);

        playlistame.setText(playlist.name);

        int number=getArguments().getInt(ARG_PAGE_NUMBER)+1;
        String playlistnumberstring;

        if (number>9){
            playlistnumberstring=String.valueOf(number);
        } else {
            playlistnumberstring="0"+String.valueOf(number);
        }
        playlistnumber.setText(playlistnumberstring);

        Random random = new Random();
        int rndInt = random.nextInt(foregroundColors.length);
        foreground.setBackgroundColor(foregroundColors[rndInt]);


        return rootView;
    }

}
