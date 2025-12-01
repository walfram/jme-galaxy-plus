package galaxy.shared.material;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.Materials;
import com.jme3.math.ColorRGBA;

public class LightingMaterial extends Material {

	public LightingMaterial(AssetManager assetManager) {
		super(assetManager, Materials.LIGHTING);
	}

	public LightingMaterial(AssetManager assetManager, ColorRGBA color) {
		this(assetManager);
		setColor("Diffuse", color);
		setColor("Ambient", color);
		setColor("Specular", color);
		setBoolean("UseMaterialColors", true);
	}

}
