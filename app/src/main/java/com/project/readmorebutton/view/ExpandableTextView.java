package com.project.readmorebutton.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;

import com.project.readmorebutton.R;

/**
 * Created by gleb on 7/10/17.
 */

public class ExpandableTextView  extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {

    private static final int DEFAULT_TRIM_LENGTH = 100;
    private static final String ELLIPSIS = " <html><body><font color='#3088CD'>read more...</font></body></html>";

    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        typedArray.recycle();

        setOnClickListener(this);
    }

    private void setText() {
        super.setText(Html.fromHtml(getDisplayableText().toString()), bufferType);
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText();
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText() {
        if (originalText != null && originalText.length() > trimLength) {
            return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
        } else {
            return originalText;
        }
    }

    @Override
    public void onClick(View view) {
        trim = !trim;
        setText();
        requestFocusFromTouch();
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = getHeight(trimLength);
        trimmedText = getTrimmedText();
        setText();
    }

    private int getHeight(int lineCount) {
        return (int) ((lineCount * getLineCount() + (lineCount > 0 ? (lineCount - 1) * getPaint().getFontSpacing() : 0)) / 1.5f);
    }
}