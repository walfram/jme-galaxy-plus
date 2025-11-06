package galaxy.shared.tween;

import com.simsilica.lemur.anim.AbstractTween;

public class CallbackTween extends AbstractTween {
	private final Runnable callback;

	public CallbackTween(Runnable callback) {
		super(0);
		this.callback = callback;
	}

	@Override
	protected void doInterpolate(double t) {
		callback.run();
	}
}
