package com.skeleton.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.model.profile.ConstantResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.customview.MaterialEditText;

import java.util.List;

/**
 * profile created
 */
public class ProfileOneFragment extends BaseFragment {
    private TextView tvheader;
    private MaterialEditText etRelationship, etEthnicity, etReligion, etHeight, etBodytype, etSmoking, etOrientation;
    private ConstantResponse responseconstant;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_one, container, false);
        init(view);
        getProfile();
        enableFoatingEditText(etRelationship, etEthnicity, etReligion, etHeight, etBodytype, etSmoking, etOrientation);
        return view;
    }

    /**
     * @param view view for initialization
     */
    private void init(final View view) {
        tvheader = (TextView) view.findViewById(R.id.custom_header);
        responseconstant = new ConstantResponse();
        tvheader.setText("Profile completeness");
        etRelationship = (MaterialEditText) view.findViewById(R.id.etRelationship);
        etEthnicity = (MaterialEditText) view.findViewById(R.id.etEthnicity);
        etReligion = (MaterialEditText) view.findViewById(R.id.etReligion);
        etHeight = (MaterialEditText) view.findViewById(R.id.etHeight);
        etBodytype = (MaterialEditText) view.findViewById(R.id.etBodytype);
        etSmoking = (MaterialEditText) view.findViewById(R.id.etSmoking);
        etOrientation = (MaterialEditText) view.findViewById(R.id.etOrientation);
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.etRelationship:
                alertDropBox("Relationship History", responseconstant.getData().getRelationshipHistory(), etRelationship);
                break;
            case R.id.etEthnicity:
                alertDropBox("Ethnicity", responseconstant.getData().getEthnicity(), etEthnicity);
                break;
            case R.id.etReligion:
                alertDropBox("Religion", responseconstant.getData().getReligion(), etReligion);
                break;
            case R.id.etHeight:
                alertDropBox("Height", responseconstant.getData().getHeight(), etHeight);
                break;
            case R.id.etBodytype:
                alertDropBox("Bodytype", responseconstant.getData().getBodyType(), etBodytype);
                break;
            case R.id.etSmoking:
                alertDropBox("Smoking", responseconstant.getData().getSmoking(), etSmoking);
                break;
            case R.id.etOrientation:
                alertDropBox("Orientation", responseconstant.getData().getOrientation(), etOrientation);
                break;
            default:
                break;

        }
    }

    /**
     * @param mTitle title of drop box
     * @param list   list of drop bar items
     * @param etItem reference to editText
     */
    public void alertDropBox(final String mTitle, final List<String> list,
                             final MaterialEditText etItem) {
        final CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mTitle);
        builder.setItems(cs, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int item) {
                etItem.setText(cs[item]);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Enable floating label for {@link MaterialEditText}
     *
     * @param editTexts :list of editText
     */
    public static void enableFoatingEditText(final MaterialEditText... editTexts) {
        for (MaterialEditText editText : editTexts) {
            editText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_HIGHLIGHT);
        }
    }

    /**
     * @return profiles
     */

    public ConstantResponse getProfile() {
        RestClient.getApiInterface().profileConstants().enqueue(new ResponseResolver<ConstantResponse>(getActivity(), true, true) {
            @Override
            public void success(final ConstantResponse constantResponse) {
                if ("200".equals(String.valueOf(constantResponse.getStatusCode()))) {
                    responseconstant = constantResponse;
                    Log.d("debug", String.valueOf(responseconstant.getMessage()));
                    etRelationship.setOnClickListener(ProfileOneFragment.this);
                    etEthnicity.setOnClickListener(ProfileOneFragment.this);
                    etReligion.setOnClickListener(ProfileOneFragment.this);
                    etBodytype.setOnClickListener(ProfileOneFragment.this);
                    etHeight.setOnClickListener(ProfileOneFragment.this);
                    etSmoking.setOnClickListener(ProfileOneFragment.this);
                    etOrientation.setOnClickListener(ProfileOneFragment.this);

                }

            }

            @Override
            public void failure(final APIError error) {
                Log.d("debug", error.getMessage());
            }
        });
        return responseconstant;
    }


}