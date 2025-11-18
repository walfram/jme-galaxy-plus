package galaxy.generator.configuration.impl;

import com.jme3.math.Vector3f;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.TextField;
import com.simsilica.lemur.ValueEditors;
import com.simsilica.lemur.style.ElementId;
import com.simsilica.lemur.value.TextFieldValueEditor;
import galaxy.generator.SeedSource;
import galaxy.generator.configuration.SeedSourceConfiguration;
import galaxy.generator.sources.CylinderSeedSource;
import galaxy.shared.functions.ObjectToString;
import galaxy.shared.functions.StringToInteger;
import jme3utilities.math.MyVector3f;
import jme3utilities.math.noise.Generator;

import java.util.Objects;

public class CylinderSeedSourceConfiguration implements SeedSourceConfiguration {

	private TextFieldValueEditor<Integer> seedCountEditor;
	private TextField vectorEditor;
	private TextFieldValueEditor<Double> lengthEditor;
	private TextFieldValueEditor<Double> radiusEditor;
	private TextFieldValueEditor<Long> seedEditor;

	@Override
	public void initControls(Container container) {
		container.addChild(new Label("Cylinder seed source configuration", new ElementId("title")));

		container.addChild(new Label("seed count"));
		seedCountEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToInteger());
		container.addChild(seedCountEditor.startEditing(16384));

		container.addChild(new Label("Vector"));
		vectorEditor = container.addChild(new TextField(Vector3f.UNIT_X.toString()));

		container.addChild(new Label("Vector length"));
		lengthEditor = ValueEditors.doubleEditor("%.02f");
		container.addChild(lengthEditor.startEditing(256.0));

		container.addChild(new Label("Radius"));
		radiusEditor = ValueEditors.doubleEditor("%.02f");
		container.addChild(radiusEditor.startEditing(32.0));

		container.addChild(new Label("seed"));
		seedEditor = new TextFieldValueEditor<>(
				o -> Objects.requireNonNull(o).toString(),
				s -> s != null ? Long.parseLong(s) : 0
		);
		seedEditor.startEditing(42L);
	}

	@Override
	public SeedSource seedSource() {
		Vector3f vector = MyVector3f.parse(vectorEditor.getText());
		vector.normalizeLocal();

		double length = lengthEditor.getObject();

		Vector3f from = vector.mult((float) length).negate();
		Vector3f to = vector.mult((float) length);

		return new CylinderSeedSource(
				seedCountEditor.getObject(),
				from,
				to,
				radiusEditor.getObject().floatValue(),
				radiusEditor.getObject().floatValue(),
				new Generator(seedEditor.getObject()));
	}
}
