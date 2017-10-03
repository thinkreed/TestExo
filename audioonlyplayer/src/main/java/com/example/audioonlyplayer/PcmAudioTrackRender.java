package thinkreed.testexo;

import java.nio.ByteBuffer;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;

/**
 * Created by thinkreed on 2017/9/30.
 */

public class PcmAudioTrackRender extends MediaCodecAudioRenderer {

    public interface MediaFrameConsumer {
        void onFrameRetrieved(Pair pair);
    }

    private boolean isConnected = false;

    private MediaFrameConsumer frameConsumer;

    public PcmAudioTrackRender(MediaCodecSelector mediaCodecSelector) {
        super(mediaCodecSelector);
    }

    public void setMediaFrameConsumer(MediaFrameConsumer consumer) {
        this.frameConsumer = consumer;
    }

    @Override
    protected boolean processOutputBuffer(long positionUs, long elapsedRealtimeUs, MediaCodec codec, ByteBuffer buffer,
                                          int bufferIndex, int bufferFlags, long bufferPresentationTimeUs,
                                          boolean shouldSkip) throws ExoPlaybackException {

        if (isConnected) {
            int size = buffer.remaining();
            byte[] chunk = new byte[size];
            buffer.get(chunk);
            buffer.clear();
            codec.releaseOutputBuffer(bufferIndex, false);
            return true;
        }

        return super.processOutputBuffer(positionUs, elapsedRealtimeUs, codec, buffer, bufferIndex, bufferFlags,
                bufferPresentationTimeUs, shouldSkip);
    }

    @Override
    protected void configureCodec(MediaCodecInfo codecInfo, MediaCodec codec, Format format, MediaCrypto crypto) {
        super.configureCodec(codecInfo, codec, format, crypto);
    }

    @Override
    protected void onInputFormatChanged(Format newFormat) throws ExoPlaybackException {
        super.onInputFormatChanged(newFormat);
    }

    @Override
    protected void onOutputFormatChanged(MediaCodec codec, MediaFormat outputFormat) throws ExoPlaybackException {
        super.onOutputFormatChanged(codec, outputFormat);
    }
}
