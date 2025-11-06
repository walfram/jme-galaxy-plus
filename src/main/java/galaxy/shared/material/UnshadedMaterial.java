package galaxy.shared.material;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.Materials;
import com.jme3.math.ColorRGBA;

public class UnshadedMaterial extends Material {
	public UnshadedMaterial(AssetManager assetManager, ColorRGBA color) {
		super(assetManager, Materials.UNSHADED);
		setColor("Color", color);
	}
}
