/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package effects;

import java.awt.Color;

import com.mindfusion.scheduling.*;


class Preset
{
	@Override()
	public String toString()
	{
		if (Name == null)
			return "(untitled)";

		return Name;
	}

	public String Name;

	// Glass
	public boolean UseGlassEffect;
	public GlassEffectType GlassEffectType;
	public boolean UsePenAsGlow;
	public Color GlowColor;
	public Color ReflectionColor;

	// Aero
	public boolean UseAeroEffect;
	public int Opacity;
	public Color InnerOutlineColor;
	public Color ShadeColor;

	// Misc
	public boolean OverridesThemeSettings;
	public Color CalendarBackground;
	public Color CalendarBorder;
	public Color ItemBorder;
	public Color ItemBackground1;
	public boolean UseItemBackground2;
	public Color ItemBackground2;

	public ThemeType Theme;
}