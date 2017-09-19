package bamfilereadtest3;

import htsjdk.samtools.*;
import htsjdk.samtools.util.Log;
import htsjdk.samtools.util.ProgressLogger;
import htsjdk.samtools.reference.FastaSequenceFile;
import htsjdk.samtools.reference.ReferenceSequence;
import htsjdk.samtools.reference.FastaSequenceIndex;
import htsjdk.samtools.reference.ReferenceSequenceFile;
import htsjdk.samtools.reference.FastaSequenceIndexCreator;
import htsjdk.samtools.reference.IndexedFastaSequenceFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class BamFileReadTest3 {
    private static final Log log = Log.getInstance(BamFileReadTest3.class);
    public static void main(String[] args) throws IOException {
        int flank = 10;
        ReferenceSequence DNAsequence;

        VariantManager variantManager = new VariantManager(new File("C:\\Users\\anwar01\\Documents\\BamFileReadProject\\BamFileReadTest3\\test_variants.vcf"));
        variantManager.setVariant(0); // set the first variant 871334
        System.out.println("Process variant: "+variantManager.getSelectedVariant().getStart()); // can get differnt information form the class

        //Path referenceFilePath = Paths.get("C:\\Users\\anwar01\\Documents\\BamFileReadProject\\ref.fasta");
        File referenceFile = new File("C:\\Users\\anwar01\\Documents\\BamFileReadProject\\BamFileReadTest3\\ref.fasta");
        
        //FastaSequenceIndexCreator.create(referenceFilePath, true);         
        IndexedFastaSequenceFile reference = new IndexedFastaSequenceFile(referenceFile);
        //System.out.println("is indexed: " + reference.isIndexed());    
        
        DNAsequence = reference.getSubsequenceAt(variantManager.getSelectedVariant().getContig(), // get the subsequence
                variantManager.getSelectedVariant().getStart()-flank, 
                variantManager.getSelectedVariant().getStart()+flank);
        
        System.out.println(DNAsequence.getBaseString());
        SamReader samReader= SamReaderFactory.makeDefault().
            validationStringency(ValidationStringency.LENIENT).
            open(new File("C:\\Users\\anwar01\\Documents\\BamFileReadProject\\BamFileReadTest3\\DA0057129_IonXpress_012_rawlib.bam"));
        SAMRecordIterator iter = samReader.query(variantManager.getSelectedVariant().getContig(),variantManager.getSelectedVariant().getStart()-flank, 
                variantManager.getSelectedVariant().getStart()+flank, false); // last boolean for sam read completely contained in interval
            
        int count = 0;
        while(iter.hasNext()) {
            SAMRecord rec= iter.next();
            int sequenceLength = rec.getReadLength();
            String sequenceRead = new String(rec.getReadBases(), "UTF-8");
            String cigar = rec.getCigarString();
            System.out.println("Length: " + sequenceLength + ", Cigar: " + cigar + ", SEQ: " + sequenceRead);
            count++;
        }
        System.out.println("Count: " + count);
    }
}