package org.apache.ctakes.gui.pipeline;

import org.apache.ctakes.gui.component.DisablerPane;
import org.apache.ctakes.gui.pipeline.bit.PipeBitPainter;
import org.apache.ctakes.gui.util.IconLoader;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * @author SPF , chip-nlp
 * @version %I%
 * @since 12/20/2016
 */
final public class PiperCreator {

   static private final Logger LOGGER = Logger.getLogger( "PiperCreator" );

   private PiperCreator() {
   }

   static private JFrame createFrame() {
      final JFrame frame = new JFrame( "cTAKES Simple Pipeline Fabricator" );
      frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
      // Use 1024 x 768 as the minimum required resolution (XGA)
      // iPhone 3 : 480 x 320 (3:2, HVGA)
      // iPhone 4 : 960 x 640  (3:2, unique to Apple)
      // iPhone 5 : 1136 x 640 (under 16:9, unique to Apple)
      // iPad 3&4 : 2048 x 1536 (4:3, QXGA)
      // iPad Mini: 1024 x 768 (4:3, XGA)
      final Dimension size = new Dimension( 800, 600 );
      frame.setSize( size );
      frame.setMinimumSize( size );
      final JMenuBar menuBar = new JMenuBar();
//      final JMenu fileMenu = new JMenu( "File" );
//      menuBar.add( fileMenu );

      frame.setJMenuBar( menuBar );
      System.setProperty( "apple.laf.useScreenMenuBar", "true" );
      return frame;
   }

   static private MainPanel2 createMainPanel() {
      return new MainPanel2();
   }

   public static void main( final String... args ) {
      try {
         UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
         UIManager.getDefaults().put( "SplitPane.border", BorderFactory.createEmptyBorder() );
      } catch ( ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException multE ) {
         LOGGER.error( multE.getLocalizedMessage() );
      }
      final JFrame frame = createFrame();
      final MainPanel2 mainPanel = createMainPanel();
      frame.add( mainPanel );
      frame.pack();
      frame.setVisible( true );
      DisablerPane.getInstance().initialize( frame );
      PipeBitPainter.getInstance().loadIcons();
      LOGGER.info( "1. Select your Apache cTAKES root directory." );
      LOGGER.info( "   It can be a pre-built binary installation or a developer sandbox." );
      LOGGER.info( "2. Select your Unified Medical Language System (UMLS) root directory." );
      LOGGER.info( "   Once selected, your UMLS database will be parsed for available content." );
      LOGGER.info( "3. Select your desired Vocabulary sources in the left table." );
      LOGGER.info( "   Recommended Vocabulary sources are pre-selected." );
      LOGGER.info( "4. Select your desired Semantic Types in the right table." );
      LOGGER.info( "   Recommended Semantic types are pre-selected." );
      LOGGER.info( "5. Type a name for your dictionary." );
      LOGGER.info( "6. Click \'Build Dictionary\'" );
      LOGGER.info( "-  You can resize this log panel by clicking the top and dragging up or down." );

      final Object[] options = { "Scan" };
      final Icon scanIcon = IconLoader.loadIcon( "org/apache/ctakes/gui/pipeline/icon/" + "FindOnPc_48.png" );
      JOptionPane.showOptionDialog( frame, "A Scan must be performed to find available Pipe Bits.\n" +
                                           "Pipe Bits are used to assemble a cTAKES Pipeline.",
            "Find Pipe Bits", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, scanIcon, options, options[ 0 ] );
      mainPanel.findPipeBits();

   }

}