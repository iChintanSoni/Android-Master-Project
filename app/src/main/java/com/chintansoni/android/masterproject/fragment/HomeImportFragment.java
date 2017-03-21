package com.chintansoni.android.masterproject.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chintansoni.android.masterproject.R;
import com.chintansoni.android.masterproject.R2;
import com.chintansoni.android.masterproject.base.BaseConstants;
import com.chintansoni.android.masterproject.base.BaseFragment;
import com.chintansoni.android.masterproject.bottomsheet.SelectImageUsingBottomSheet;
import com.chintansoni.android.masterproject.util.File.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Chintan Soni - Senior Software Engineer (Android).
 */

public class HomeImportFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, SelectImageUsingBottomSheet.OnSelectUsingListener {

    @BindView(R2.id.iv_import)
    AppCompatImageView mAppCompatImageView;

    private String mCurrentPhotoPath;

    public static Fragment getFragment() {
        return new HomeImportFragment();
    }

    @OnClick(R2.id.iv_import)
    void onImageClick() {
        checkStoragePermission();
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_import;
    }

    private void checkStoragePermission() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            // Already have permission, do the thing
            initializeSelectImage();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_storage), BaseConstants.REQUEST_CODE_PERMISSION_STORAGE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        initializeSelectImage();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, getString(R.string.rationale_never_ask_storage))
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setPositiveButton(getString(R.string.title_settings_button_setting))
                    .setNegativeButton(getString(R.string.title_settings_button_cancel), null /* click listener */)
                    .setRequestCode(BaseConstants.REQUEST_CODE_SETTINGS_PERMISSION_STORAGE)
                    .build()
                    .show();
        }
    }

    public void initializeSelectImage() {
        SelectImageUsingBottomSheet selectImageUsingBottomSheet = new SelectImageUsingBottomSheet();
        selectImageUsingBottomSheet.setmOnSelectUsingListener(this);
        showDialogFragment(selectImageUsingBottomSheet);
    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, BaseConstants.REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    public void onCameraClick() {
        captureImage();
    }

    @Override
    public void onGalleryClick() {
        pickImage();
    }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, BaseConstants.REQUEST_CODE_CAPTURE_IMAGE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg",/* suffix */
                storageDir/* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mBaseAppCompatActivity.sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseConstants.REQUEST_CODE_CAPTURE_IMAGE && resultCode == Activity.RESULT_OK) {
            galleryAddPic();
            Glide.with(getContext())
                    .load(mCurrentPhotoPath)
                    .into(mAppCompatImageView);
        } else if (requestCode == BaseConstants.REQUEST_CODE_SETTINGS_PERMISSION_STORAGE) {
            checkStoragePermission();
        } else if (requestCode == BaseConstants.REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mCurrentPhotoPath = FileUtils.getFile(getContext(), data.getData()).getAbsolutePath();
                Glide.with(getContext())
                        .load(mCurrentPhotoPath)
                        .into(mAppCompatImageView);
            }
        }
    }
}
