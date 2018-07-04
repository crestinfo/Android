package com.crest.contactslistapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    Map<String, Integer> contactMap;
    private SectionedRecyclerViewAdapter sectionAdapter;
    LinearLayoutManager layoutManager;
    List<String> indexList;
    Map<String, Integer> indexMap;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewContact);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        sectionAdapter = new SectionedRecyclerViewAdapter();
        indexMap = new HashMap<>();
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            List<String> contacts = getContactsWithLetter(alphabet);
            if (String.valueOf(alphabet).equals("A")) {
                if (contacts.size() > 0) {
                    //count = count + contacts.size();
                    sectionAdapter.addSection(new ContactsSection(String.valueOf(alphabet), contacts));
                    indexMap.put(String.valueOf(alphabet), 0);
                }
            } else {
                if (contacts.size() > 0) {
                    sectionAdapter.addSection(new ContactsSection(String.valueOf(alphabet), contacts));
                    //indexMap.put(String.valueOf(alphabet), count - 1);
                    int newCount = contacts.size() - 1;
                    indexMap.put(String.valueOf(alphabet), count + newCount);
                }
            }
        }
        recyclerView.setAdapter(sectionAdapter);
        getIndexList(getResources().getStringArray(R.array.contacts));
        displayIndex();
    }

    private List<String> getContactsWithLetter(char letter) {
        List<String> contacts = new ArrayList<>();
        for (String contact : getResources().getStringArray(R.array.contacts)) {
            if (contact.charAt(0) == letter) {
                contacts.add(contact);
                if (!String.valueOf(letter).equals("A"))
                    count = count + 1;
            }
        }
        return contacts;
    }

    private void getIndexList(String[] contacts) {
        contactMap = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < contacts.length; i++) {
            String names = contacts[i];
            String index = names.substring(0, 1);
            if (contactMap.get(index) == null)
                contactMap.put(index, i);
        }
    }


    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index_layout);

        TextView textView;
        indexList = new ArrayList<String>(contactMap.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_textview, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }

    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
            String key = entry.getKey();
            if (key.equals(selectedIndex.getText())) {
                if (selectedIndex.getText() == "A") {
                    layoutManager.scrollToPositionWithOffset(0, 0);
                } else {
                    layoutManager.scrollToPositionWithOffset(entry.getValue(), 0);
                    Log.e("Entry", "Key: " + entry.getKey() + " Value: " + entry.getValue());
                }
            }
        }
    }


    private class ContactsSection extends StatelessSection {

        String title;
        List<String> list;

        ContactsSection(String title, List<String> list) {
            super(R.layout.list_header, R.layout.list_item);

            this.title = title;
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            itemHolder.tvItem.setText(list.get(position));

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
                    Log.e("ITEM", "onClick: " + position);
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.txtContactName);
        }

    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvItem;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvItem = (TextView) view.findViewById(R.id.txtContactHeader);
        }
    }
}
