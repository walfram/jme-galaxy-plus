package galaxy.proto.game;

import com.simsilica.lemur.anim.AbstractTween;

public class CallbackTween extends AbstractTween {
	private final Runnable callback;

	protected CallbackTween(Runnable callback) {
		super(0);
		this.callback = callback;
	}

	@Override
	protected void doInterpolate(double t) {
		callback.run();
	}
}
