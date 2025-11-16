package galaxy.generator.configuration.impl;

import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.ValueEditors;
import com.simsilica.lemur.style.ElementId;
import com.simsilica.lemur.value.TextFieldValueEditor;
import galaxy.generator.SeedSource;
import galaxy.generator.configuration.SeedSourceConfiguration;
import galaxy.generator.sources.SimpleSeedSource;
import galaxy.shared.functions.ObjectToString;
import galaxy.shared.functions.StringToInteger;
import galaxy.shared.functions.StringToLong;

public class SimpleSeedSourceConfiguration implements SeedSourceConfiguration {

	private TextFieldValueEditor<Integer> seedCountEditor;
	private TextFieldValueEditor<Double> scaleEditor;
	private TextFieldValueEditor<Long> seedEditor;

	@Override
	public void initControls(Container container) {
		container.addChild(new Label("Simple seed source configuration", new ElementId("title")));

		container.addChild(new Label("seed count"));
		seedCountEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToInteger());
		container.addChild(seedCountEditor.startEditing(16384));

		container.addChild(new Label("scale"));
		scaleEditor = ValueEditors.doubleEditor("%.02f");
		scaleEditor.startEditing(256.0);
		container.addChild(scaleEditor.getEditor());

		container.addChild(new Label("seed"));
		seedEditor = new TextFieldValueEditor<>(new ObjectToString(), new StringToLong());
		seedEditor.startEditing(42L);
		container.addChild(seedEditor.getEditor());
	}

	@Override
	public SeedSource seedSource() {
		return new SimpleSeedSource(seedCountEditor.getObject(), scaleEditor.getObject(), seedEditor.getObject());
	}
}
