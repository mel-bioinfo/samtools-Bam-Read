package bamfilereadtest3;

import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFFileReader;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author ranasi01
 */
public class VariantManager {
    private VariantContext selectedVariant;
    private ArrayList<VariantContext> variants;
    
    public int TotalVariantCount = 0;
    
    public VariantManager(File vcfFile){
        DebugInfo vars = new DebugInfo();
        variants = new ArrayList();
        VCFFileReader reader = new VCFFileReader(vcfFile, false);
        for(VariantContext context:reader.iterator().toList()){ 
            variants.add(context);
            TotalVariantCount++;
            vars.addText(context+"\n");
        }
        vars.createFile("vcf contents");
    }
    
    public VariantContext getSelectedVariant(){
        return selectedVariant;
    }
    
    public void setVariant(int index){
        selectedVariant = variants.get(index);
    }
}
