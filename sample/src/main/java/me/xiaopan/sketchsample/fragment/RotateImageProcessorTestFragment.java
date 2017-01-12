package me.xiaopan.sketchsample.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import me.xiaopan.androidinjector.InjectContentView;
import me.xiaopan.androidinjector.InjectView;
import me.xiaopan.sketch.display.TransitionImageDisplayer;
import me.xiaopan.sketch.process.RotateImageProcessor;
import me.xiaopan.sketchsample.AssetImage;
import me.xiaopan.sketchsample.MyFragment;
import me.xiaopan.sketchsample.R;
import me.xiaopan.sketchsample.widget.MyImageView;

@InjectContentView(R.layout.fragment_rotate)
public class RotateImageProcessorTestFragment extends MyFragment {
    @InjectView(R.id.image_rotateFragment)
    MyImageView imageView;

    @InjectView(R.id.button_rotateFragment)
    Button rotateButton;

    private int degrees = 45;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 缩小图片，处理速度更快，更少的内存消耗
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        imageView.getOptions().setMaxSize(metrics.widthPixels / 2, metrics.heightPixels / 2);

        imageView.getOptions().setImageDisplayer(new TransitionImageDisplayer());

        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                degrees += 45;
                apply();
            }
        });

        apply();
    }

    private void apply(){
        imageView.getOptions().setImageProcessor(new RotateImageProcessor(degrees));
        imageView.displayImage(AssetImage.MEI_NV);
    }
}
