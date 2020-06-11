/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Commande;
import Services.ServiceCommande;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Stat extends Form{
    
    Font smallFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_SMALL, Font.STYLE_PLAIN);
    Font medFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_MEDIUM, Font.STYLE_PLAIN);
    Font largeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_LARGE, Font.STYLE_PLAIN);
    Resources theme=UIManager.initFirstTheme("/theme");
    Form current;

    
    
    public Stat(int annee2,Form previous)  
    {   current =this;
        this.getAllStyles().setBgImage(theme.getImage("banner-1.jpg"));   
        
        setTitle("Stat"+annee2);
        setLayout(BoxLayout.y());
        
        
        int annee1=annee2-1;
        String[] titles = new String[]{annee1+"", annee2+""};
    List<double[]> values = new ArrayList<double[]>();
        
        
        //compteur annee 1
        int nbjanvier=0;
        int nbfev=0;
        int nbmars=0;
        int nbavril=0;
        int nbmai=0;
        int nbjuin=0;
        int nbajuillet=0;
        int nbaout=0;
        int nbsept=0;
        int nboct=0;
        int nbnov=0;
        int nbdec=0;
        
        //compteur annee 2
        int nbjanvier1=0;
        int nbfev1=0;
        int nbmars1=0;
        int nbavril1=0;
        int nbmai1=0;
        int nbjuin1=0;
        int nbajuillet1=0;
        int nbaout1=0;
        int nbsept1=0;
        int nboct1=0;
        int nbnov1=0;
        int nbdec1=0;
        
        
        
        
       //préparer les valeur de année 2
       //janivier
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-01-01",annee2+"-01-31",2))
        { 
          nbjanvier1++;
        }
       //fev
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-02-01",annee2+"-02-29",2))
        { 
          nbfev1++;
        }
       //mars
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-03-01",annee2+"-03-31",2))
        { 
          nbmars1++;
        }
       //avril
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-04-01",annee2+"-04-30",2))
        { 
          nbavril1++;
        }
       //mai
        for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-05-01",annee2+"-05-31",2))
        { 
          nbmai1++;
        }
        
       //juin
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-06-01",annee2+"-06-31",2))
        { 
          nbjuin1++;
        }
       //juillet
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-07-01",annee2+"-07-30",2))
        { 
          nbajuillet1++;
        }
       //aout
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-08-01",annee2+"-08-31",2))
        { 
          nbaout1++;
        }
       //septembre
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-09-01",annee2+"-09-31",2))
        { 
          nbsept1++;
        }
       //octobre
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-10-01",annee2+"-10-31",2))
        { 
          nboct1++;
        }
       //nov
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-11-01",annee2+"-11-30",2))
        { 
          nbnov1++;
        }
       //decembre
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-12-01",annee2+"-12-31",2))
        { 
          nbjanvier1++;
        }
       
       
       
        //préparer les valeur de année 1
       //janivier
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-01-01",annee1+"-01-31",2))
        { 
          nbjanvier++;
        }
       //fev
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-02-01",annee1+"-02-29",2))
        { 
          nbfev++;
        }
       //mars
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-03-01",annee1+"-03-31",2))
        { 
          nbmars++;
        }
       //avril
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-04-01",annee1+"-04-30",2))
        { 
          nbavril++;
        }
       //mai
        for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-05-01",annee1+"-05-31",2))
        { 
          nbmai++;
        }
        
       //juin
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-06-01",annee1+"-06-31",2))
        { 
          nbjuin++;
        }
       //juillet
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-07-01",annee1+"-07-30",2))
        { 
          nbajuillet++;
        }
       //aout
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-08-01",annee1+"-08-31",2))
        { 
          nbaout++;
        }
       //septembre
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-09-01",annee1+"-09-31",2))
        { 
          nbsept++;
        }
       //octobre
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-10-01",annee1+"-10-31",2))
        { 
          nboct++;
        }
       //nov
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-11-01",annee1+"-11-30",2))
        { 
          nbnov++;
        }
       //decembre
       for(Commande c :ServiceCommande.getInstance().getCommandeMonth(annee2+"-12-01",annee1+"-12-31",2))
        { 
          nbjanvier++;
        }
        
    
    
    
    values.add(new double[]{nbjanvier, nbfev, nbmars, nbavril, nbmai, nbjuin, nbajuillet, nbaout, nbsept, nboct,
    nbnov, nbjanvier});
    values.add(new double[]{nbjanvier1, nbfev1, nbmars1, nbavril1, nbmai1, nbjuin1, nbajuillet1, nbaout1, nbsept1, nboct1,
     nbnov1, nbjanvier1}); 
    int[] colors = new int[]{ColorUtil.CYAN, ColorUtil.BLUE};
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
    renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
    setChartSettings(renderer, "Mes Achats dans les deux dernieres anneésZ", "Month", "Nombre Du commandes", 0.5,
    12.5, 0, 100, ColorUtil.GRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(1);
        renderer.setYLabels(10);
        renderer.addXTextLabel(1, "Jan");

        renderer.addXTextLabel(3, "Mar");

        renderer.addXTextLabel(5, "May");

        renderer.addXTextLabel(7, "Juil");

        renderer.addXTextLabel(9, "Sept");

        renderer.addXTextLabel(11, "Nov");

        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
         for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }
      BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
               BarChart.Type.DEFAULT);
     ChartComponent c = new ChartComponent(chart);
        this.add(c);
        
        
                Toolbar tb = this.getToolbar();
         Image icon = theme.getImage("icon.png"); 
    Container topBar = BorderLayout.east(new Label(icon));
    topBar.add(BorderLayout.SOUTH, new Label("Velo.tn", "SidemenuTagline")); 
    topBar.setUIID("SideCommand");
    tb.addComponentToSideMenu(topBar);

    tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (e) -> 
    {
        new AfficherProduits().show();
    }
   ); 
    tb.addMaterialCommandToSideMenu("Mes Commandes", FontImage.MATERIAL_SHOPPING_CART, e -> {new AfficherCommande().show();});
    tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e -> {});
    tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {});

         
         getToolbar().addMaterialCommandToOverflowMenu("Voir Panier",
                FontImage.MATERIAL_SHOPPING_CART,(e)->{
                
                new AfficherPanier(current).show();
                });
         
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (e)->{
        
        previous.showBack();
        });
        
    }
    
    
    
    protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(smallFont.getHeight() / 2);
        renderer.setChartTitleTextFont(smallFont);
        renderer.setLabelsTextSize(smallFont.getHeight() / 2);
        renderer.setLegendTextSize(smallFont.getHeight() / 2);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
    
    
    
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
            String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
            int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }

    
    
    protected void initRendererer(DefaultRenderer renderer) {
        renderer.setBackgroundColor(0xffffffff);
        renderer.setApplyBackgroundColor(true);
        renderer.setLabelsColor(0xff000000);
        renderer.setAxesColor(0xff000000);
        if(Font.isNativeFontSchemeSupported()) {
            Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                    derive(Display.getInstance().convertToPixels(2.5f), Font.STYLE_PLAIN);
            renderer.setTextTypeface(fnt);
            renderer.setChartTitleTextFont(fnt);
            renderer.setLabelsTextFont(fnt);
            renderer.setLegendTextFont(fnt);

            if(renderer instanceof XYMultipleSeriesRenderer) {
                ((XYMultipleSeriesRenderer)renderer).setAxisTitleTextFont(fnt);
            }
            if(renderer instanceof XYMultipleSeriesRenderer) {
                XYMultipleSeriesRenderer x = (XYMultipleSeriesRenderer)renderer;
                x.setMarginsColor(0xfff7f7f7);
                x.setXLabelsColor(0xff000000);
                x.setYLabelsColor(0, 0xff000000);
            }
        }

    }
    
    
    
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }
    
}
