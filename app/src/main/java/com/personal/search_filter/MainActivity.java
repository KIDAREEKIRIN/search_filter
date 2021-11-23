package com.personal.search_filter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Shape> shapeList = new ArrayList<Shape>();

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSearchWidgets();

        setupData();

        setUpList();

        setUpOnclickListener();

    }

    private void initSearchWidgets()
    {
        SearchView searchView = (SearchView) findViewById(R.id.shapeListSearchView);

        // setOnActionExpandListener - 메뉴의 확장과 축소 이벤트 관리.

        // 검색, 변경 이벤트 관리.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Shape> filteredShapes = new ArrayList<Shape>();

                for(Shape shape: shapeList)
                {
                    if(shape.getName().toLowerCase().contains(newText.toLowerCase()))
                    {
                        filteredShapes.add(shape);
                    }
                }

                ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), 0,filteredShapes);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }


    private void setupData()
    {
        Shape circle = new Shape("0","Circle", R.drawable.circle);
        shapeList.add(circle);

        Shape triangle = new Shape("1","Triangle", R.drawable.triangle);
        shapeList.add(triangle);

        Shape square = new Shape("2","Square", R.drawable.square);
        shapeList.add(square);

        Shape rectangle = new Shape("3","Rectangle", R.drawable.rectangle);
        shapeList.add(rectangle);

        Shape octagon = new Shape("4","Octagon", R.drawable.octagon);
        shapeList.add(octagon);

        Shape circle2 = new Shape("0","Circle 2", R.drawable.circle);
        shapeList.add(circle2);

        Shape triangle2 = new Shape("1","Triangle 2", R.drawable.triangle);
        shapeList.add(triangle2);

        Shape square2 = new Shape("2","Square 2", R.drawable.square);
        shapeList.add(square2);

        Shape rectangle2 = new Shape("3","Rectangle 2", R.drawable.rectangle);
        shapeList.add(rectangle2);

        Shape octagon2 = new Shape("4","Octagon 2", R.drawable.octagon);
        shapeList.add(octagon2);
    }

    private void setUpList()
    {
        listView = (ListView) findViewById(R.id.shapesListView);

        ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), 0, shapeList);
        listView.setAdapter(adapter);
    }

    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Shape selectShape = (Shape) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(),DetailActivity.class);
                showDetail.putExtra("id",selectShape.getId());
                startActivity(showDetail);
            }
        });
    }
}