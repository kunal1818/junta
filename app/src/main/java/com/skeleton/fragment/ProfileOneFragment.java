package com.skeleton.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.model.profile.ConstantResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.customview.MaterialEditText;

import java.util.List;

import io.paperdb.Paper;

/**
 * profile created
 */
public class ProfileOneFragment extends BaseFragment {
    private TextView tvheader;
    private TextView tvRelationship, tvEthnicity, tvReligion, tvHeight, tvBodytype, tvSmoking, tvOrientation;
    private MaterialEditText etRelationship, etEthnicity, etReligion, etHeight, etBodytype, etSmoking, etOrientation;
    private ConstantResponse responseconstant;
    private Button btnNext;
    private String accessToken;

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
        tvRelationship = (TextView) view.findViewById(R.id.tvRelation);
        tvEthnicity = (TextView) view.findViewById(R.id.tvEthnicity);
        tvReligion = (TextView) view.findViewById(R.id.tvReligion);
        tvHeight = (TextView) view.findViewById(R.id.tvHeight);
        tvBodytype = (TextView) view.findViewById(R.id.tvBodytype);
        tvSmoking = (TextView) view.findViewById(R.id.tvSmoking);
        tvOrientation = (TextView) view.findViewById(R.id.tvOrientation);
        btnNext = (Button) view.findViewById(R.id.btn_next);
        accessToken = Paper.book().read("key");

    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.etRelationship:
                alertDropBox("Relationship History", responseconstant.getData().getRelationshipHistory(), etRelationship, tvRelationship);

                break;
            case R.id.etEthnicity:
                alertDropBox("Ethnicity", responseconstant.getData().getEthnicity(), etEthnicity, tvEthnicity);

                break;
            case R.id.etReligion:
                alertDropBox("Religion", responseconstant.getData().getReligion(), etReligion, tvReligion);

                break;
            case R.id.etHeight:
                alertDropBox("Height", responseconstant.getData().getHeight(), etHeight, tvHeight);

                break;
            case R.id.etBodytype:
                alertDropBox("Bodytype", responseconstant.getData().getBodyType(), etBodytype, tvBodytype);

                break;
            case R.id.etSmoking:
                alertDropBox("Smoking", responseconstant.getData().getSmoking(), etSmoking, tvSmoking);
                break;
            case R.id.etOrientation:
                alertDropBox("Orientation", responseconstant.getData().getOrientation(), etOrientation, tvOrientation);
                break;
            case R.id.btn_next:
                updateInfo();
                break;
            default:
                break;

        }
    }

    /**
     * @param mTitle    title of drop box
     * @param list      list of drop bar items
     * @param etItem    reference to editText
     * @param tvheadBar headBar of ProfileOne
     */
    public void alertDropBox(final String mTitle, final List<String> list,
                             final MaterialEditText etItem, final TextView tvheadBar) {
        final CharSequence[] cs = list.toArray(new CharSequence[list.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mTitle);
        builder.setItems(cs, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int item) {
                etItem.setText(cs[item]);
                tvheadBar.setBackgroundResource(R.color.colorPrimaryDark);
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
                    btnNext.setOnClickListener(ProfileOneFragment.this);

                }

            }

            @Override
            public void failure(final APIError error) {
                Log.d("debug", error.getMessage());
            }
        });
        return responseconstant;
    }

    /**
     * update information of profile one from here
     */
    public void updateInfo() {
        MultipartParams params = new MultipartParams.Builder()
                .add("RelationshipHistory", etRelationship)
                .add("Ethnicity", etEthnicity)
                .add("Religion", etReligion)
                .add("BodyType", etBodytype)
                .add("Smoking", etSmoking)
                .add("Height", etHeight)
                .add("Orientation", etOrientation).build();
        RestClient.getApiInterface().update_profie(accessToken, params.getMap())
                .enqueue(new ResponseResolver<ConstantResponse>(getContext(), true, true) {
                    @Override
                    public void success(final ConstantResponse constantResponse) {
                        Toast.makeText(getContext(), "profile updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(final APIError error) {

                    }
                });


    }

}