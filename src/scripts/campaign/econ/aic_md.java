package scripts.campaign.econ;

import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

public class aic_md extends BaseMarketConditionPlugin {

    public static final float JC_DEFENCE_BONUS = 0.30f;
    public static final float JC_FLEET_BONUS = 0.15f;

    @Override
    public void apply(String id) {
        super.apply(id);

        if (!market.getFactionId().contentEquals("aic"))
        {
            unapply(id);
            return;
        }

        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD)
                .modifyMult(getModId(), 1f + JC_DEFENCE_BONUS,"Bonus");
        market.getStats().getDynamic().getMod(Stats.COMBAT_FLEET_SIZE_MULT)
                .modifyMultAlways(id, 1f + JC_FLEET_BONUS,"Bonus");

    }

    @Override
    public boolean showIcon() {
        return market.getFactionId().contentEquals("aic");
    }

    @Override
    public void unapply(String id) {
        super.unapply(id);

        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).unmodify(id);
        market.getStats().getDynamic().getMod(Stats.COMBAT_FLEET_SIZE_MULT).unmodify(id);

    }

    @Override
    protected void createTooltipAfterDescription(TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltipAfterDescription(tooltip, expanded);

        if (market == null) {
            return;
        }


        tooltip.addPara("%s Ground Defenses",
                10f, Misc.getHighlightColor(),
                "+" + (int) Math.round(JC_DEFENCE_BONUS * 100f)  + "%");

        tooltip.addPara("%s Fleet Size",
                10f, Misc.getHighlightColor(),
                "+" + (int) Math.round(JC_FLEET_BONUS * 100f) + "%");


    }
}
