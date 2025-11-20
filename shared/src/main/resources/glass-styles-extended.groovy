import com.jme3.math.ColorRGBA
import com.simsilica.lemur.Button
import com.simsilica.lemur.Button.ButtonAction
import com.simsilica.lemur.Command
import com.simsilica.lemur.HAlignment
import com.simsilica.lemur.Insets3f
import com.simsilica.lemur.component.IconComponent
import com.simsilica.lemur.component.QuadBackgroundComponent
import com.simsilica.lemur.component.TbtQuadBackgroundComponent
import com.simsilica.lemur.component.SpringGridLayout

selector("glass") {
    fontSize = 16
}

selector("label", "glass") {
    insets = new Insets3f(2, 2, 2, 2);
    color = color(0.5, 0.75, 0.75, 0.85)
    background = new QuadBackgroundComponent(color(0.125, 0.157, 0.204, 1.0), 10, 5)
}

//selector( "table", "label", "glass" ) {
//    insets = new Insets3f(5, 10, 5, 10)
//    color = color(1, 0, 0, 0)
//}

selector("table", "align-right", "glass") {
    textHAlignment = HAlignment.Right
}

selector("container", "table", "glass") {
    layout = new SpringGridLayout()
}

selector("container", "glass") {
     // background = new QuadBackgroundComponent(color(0.071, 0.086, 0.11, 1.0), 0, 0)
    background = new QuadBackgroundComponent(color(0.5, 0.5, 0.5, 1.0), 0, 0)
     insets = new Insets3f(5, 5, 5, 5)

//    background = TbtQuadBackgroundComponent.create(
//            texture(name: "kenney-nl-space-pack-ui/panel_square.png", generateMips: false),
//            1, 6, 5, 58, 58, 1f, false
//    )
}

selector("slider", "glass") {
    // background = gradient.clone()
    // background.setColor(color(0.25, 0.5, 0.5, 0.5))
}

def pressedCommand = new Command<Button>() {
    public void execute(Button source) {
        if (source.isPressed()) {
            source.move(1, -1, 0);
        } else {
            source.move(-1, 1, 0);
        }
    }
};

def repeatCommand = new Command<Button>() {
    private long startTime;
    private long lastClick;

    public void execute(Button source) {
        // Only do the repeating click while the mouse is
        // over the button (and pressed of course)
        if (source.isPressed() && source.isHighlightOn()) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            // After half a second pause, click 8 times a second
            if (elapsedTime > 500) {
                if (elapsedTime - lastClick > 125) {
                    source.click();

                    // Try to quantize the last click time to prevent drift
                    lastClick = ((elapsedTime - 500) / 125) * 125 + 500;
                }
            }
        } else {
            startTime = System.currentTimeMillis();
            lastClick = 0;
        }
    }
};

def stdButtonCommands = [
        (ButtonAction.Down): [pressedCommand],
        (ButtonAction.Up)  : [pressedCommand]
];

def sliderButtonCommands = [
        (ButtonAction.Hover): [repeatCommand]
];

selector("title", "glass") {
    color = color(0.8, 0.9, 1, 0.85f)
    highlightColor = color(1, 0.8, 1, 0.85f)
    shadowColor = color(0, 0, 0, 0.75f)
    // shadowOffset = new com.jme3.math.Vector3f(2, -2, -1);
    background = new QuadBackgroundComponent(color(0.176, 0.431, 0.549, 1.0));
//    background.texture = texture( name:"kenney-nl-space-pack-ui/button_rectangle.png",
//                                  generateMips:false )

    insets = new Insets3f(2, 2, 2, 2);
    background.setMargin(10, 5)

    buttonCommands = stdButtonCommands;
}


selector("button", "glass") {
    color = color(0.2, 0.2, 0.2, 0.95f)

//    background = new QuadBackgroundComponent(color(0.000, 0.706, 0.784, 1.0), 10, 5)
    // background.setColor(color(0, 0.75, 0.75, 0.5))
    background = TbtQuadBackgroundComponent.create(
            texture(name: "kenney-nl-space-pack-ui/panel_glass.png", generateMips: false),
            1, 5, 5, 59, 59, 1f, false
    )

    insets = new Insets3f(2, 2, 2, 2)
    highlightColor = ColorRGBA.Red

    buttonCommands = stdButtonCommands;
}

selector("slider", "glass") {
    insets = new Insets3f(1, 3, 1, 2);
}

selector("slider", "button", "glass") {
    // background = doubleGradient.clone()
    // background.setColor(color(0.5, 0.75, 0.75, 0.5))
    insets = new Insets3f(0, 0, 0, 0);
}

selector("slider.thumb.button", "glass") {
    text = "[]"
    color = color(0.6, 0.8, 0.8, 0.85)
}

selector("slider.left.button", "glass") {
    text = "-"
    // background = doubleGradient.clone()
    //background.setColor(color(0.5, 0.75, 0.75, 0.5))
    // background.setMargin(5, 0);
    color = color(0.6, 0.8, 0.8, 0.85)

    buttonCommands = sliderButtonCommands;
}

selector("slider.right.button", "glass") {
    text = "+"
    // background = doubleGradient.clone()
    //background.setColor(color(0.5, 0.75, 0.75, 0.5))
    //background.setMargin(4, 0);
    color = color(0.6, 0.8, 0.8, 0.85)

    buttonCommands = sliderButtonCommands;
}

selector("slider.up.button", "glass") {
    buttonCommands = sliderButtonCommands;
}

selector("slider.down.button", "glass") {
    buttonCommands = sliderButtonCommands;
}

selector("checkbox", "glass") {
    def on = new IconComponent("/com/simsilica/lemur/icons/Glass-check-on.png", 1f,
            0, 0, 1f, false);
    on.setColor(color(0.5, 0.9, 0.9, 0.9))
    on.setMargin(5, 0);
    def off = new IconComponent("/com/simsilica/lemur/icons/Glass-check-off.png", 1f,
            0, 0, 1f, false);
    off.setColor(color(0.6, 0.8, 0.8, 0.8))
    off.setMargin(5, 0);

    onView = on;
    offView = off;

    color = color(0.8, 0.9, 1, 0.85f)
}

selector("rollup", "glass") {
    // background = gradient.clone()
    // background.setColor(color(0.25, 0.5, 0.5, 0.5))
}

selector("tabbedPanel", "glass") {
    activationColor = color(0.8, 0.9, 1, 0.85f)
}

selector("tabbedPanel.container", "glass") {
    background = null
}

selector("tab.button", "glass") {
    // background = gradient.clone()
    // background.setColor(color(0.25, 0.5, 0.5, 0.5))
    color = color(0.4, 0.45, 0.5, 0.85f)
    insets = new Insets3f(4, 2, 0, 2);

    buttonCommands = stdButtonCommands;
}


