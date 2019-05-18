package com.eligasht.reservation.views.adapters.homeFeatureAdapter;

public class HomeFeatureModels {



        String PropertyTitle;
        String PropertyCat;
        String Image;
        String PropertyDescription;
        int CategoryID;

        public HomeFeatureModels(String propertyTitle, String propertyCat, String image, String propertyDescription, int categoryID) {
            PropertyTitle = propertyTitle;
            PropertyCat = propertyCat;
            Image = image;
            PropertyDescription = propertyDescription;
            CategoryID = categoryID;
        }

    public HomeFeatureModels() {

    }


    public String getPropertyTitle() {
            return PropertyTitle;
        }

        public void setPropertyTitle(String propertyTitle) {
            PropertyTitle = propertyTitle;
        }

        public String getPropertyCat() {
            return PropertyCat;
        }

        public void setPropertyCat(String propertyCat) {
            PropertyCat = propertyCat;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public String getPropertyDescription() {
            return PropertyDescription;
        }

        public void setPropertyDescription(String propertyDescription) {
            PropertyDescription = propertyDescription;
        }

        public int getCategoryID() {
            return CategoryID;
        }

        public void setCategoryID(int categoryID) {
            CategoryID = categoryID;
        }
    }


