package com.example.travelassistant;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalAdviceActivity extends AppCompatActivity {
    private EditText etMedicalQuestion;
    private RecyclerView messageRecyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messages;
    private Toolbar toolbar;

    private static final String API_URL = "https://api.moonshot.cn/v1/chat/completions"; // 替换为您的API URL
    private static final String API_KEY = "sk-nRJGpJgxZUIfp6g9LewWSVYXK2cIxVqfVcMCfOtewunkKxTj"; // 替换为您的API Key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicaladvice);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Hide the default title

        etMedicalQuestion = findViewById(R.id.etMedicalQuestion);
        messageRecyclerView = findViewById(R.id.messageRecyclerView);

        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(messages);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(messageAdapter);

        // Set up the send button listener
        findViewById(R.id.btnSubmit).setOnClickListener(v -> {
            String question = etMedicalQuestion.getText().toString();
            if (!question.isEmpty()) {
                addMessage(new Message(question, true)); // Add user message
                requestMedicalAdvice(question); // Request medical advice
                etMedicalQuestion.setText("");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close this activity and return to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addMessage(Message message) {
        int position = messages.size();
        messages.add(message);
        messageAdapter.notifyItemInserted(position);
        messageRecyclerView.scrollToPosition(position);
    }

    private void requestMedicalAdvice(String question) {
        JSONObject jsonRequest = new JSONObject();
        try {
            // 创建一个包含对话历史的消息列表
            JSONArray messagesArray = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", question);
            messagesArray.put(userMessage);

            // 添加模型ID，选择一个适合您需求的模型
            String modelId = "moonshot-v1-8k"; // 或 "moonshot-v1-32k" 或 "moonshot-v1-128k"

            // 构建请求体
            jsonRequest.put("messages", messagesArray);
            jsonRequest.put("model", modelId);
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(new Message("Error: Unable to create request", false));
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("API_RESPONSE", response.toString()); // 打印完整的响应内容
                        try {
                            // 从choices数组中获取第一个元素
                            JSONObject firstChoice = response.getJSONArray("choices").getJSONObject(0);
                            // 获取助手的回答
                            String answer = firstChoice.getJSONObject("message").getString("content");
                            addMessage(new Message(answer, false)); // Add assistant message
                        } catch (Exception e) {
                            e.printStackTrace();
                            addMessage(new Message("Error: Unable to retrieve response", false));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    int statusCode = error.networkResponse.statusCode;
                    Log.e("API_ERROR", "Status Code: " + statusCode);
                    // 根据状态码提供更具体的错误信息
                }
                addMessage(new Message("Error: " + error.getMessage(), false));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + API_KEY);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(jsonObjectRequest);
    }
}