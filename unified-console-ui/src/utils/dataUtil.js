const TimeUtils = {
  // 获取当前时间
  now() {
    return new Date();
  },

  // 格式化日期
  format(date = new Date(), formatStr = 'YYYY-MM-DD HH:mm:ss') {
    const pad = (n) => n.toString().padStart(2, '0');

    const map = {
      YYYY: date.getFullYear(),
      MM: pad(date.getMonth() + 1),
      DD: pad(date.getDate()),
      HH: pad(date.getHours()),
      mm: pad(date.getMinutes()),
      ss: pad(date.getSeconds()),
      D: date.getDate(),
      M: date.getMonth() + 1,
      H: date.getHours(),
      m: date.getMinutes(),
      s: date.getSeconds(),
      dddd: date.toLocaleDateString('zh-CN', { weekday: 'long' })
    };

    return Object.entries(map).reduce(
      (acc, [k, v]) => acc.replace(new RegExp(k, 'g'), v),
      formatStr
    );
  },

  // 日期加减（支持 days, hours, minutes, seconds）
  add(date = new Date(), value = 0, unit = 'days') {
    const newDate = new Date(date);
    const unitMap = {
      days: 86400000,
      hours: 3600000,
      minutes: 60000,
      seconds: 1000,
    };
    newDate.setTime(newDate.getTime() + value * (unitMap[unit] || 0));
    return newDate;
  },

  // 时间差（返回整数）
  diff(date1, date2, unit = 'days') {
    const d1 = new Date(date1);
    const d2 = new Date(date2);
    const diffMs = d1.getTime() - d2.getTime();

    const units = {
      days: 86400000,
      hours: 3600000,
      minutes: 60000,
      seconds: 1000,
    };

    return Math.floor(diffMs / (units[unit] || units.days));
  },

  // 获取本月最后一天
  getLastDayOfMonth(date = new Date()) {
    return new Date(date.getFullYear(), date.getMonth() + 1, 0);
  },

  // 时间戳转日期
  fromTimestamp(timestamp) {
    return new Date(timestamp);
  },

  // 日期转时间戳
  toTimestamp(date = new Date()) {
    return new Date(date).getTime();
  },

  // 相对时间描述（如“3天前”）
  formatRelativeTime(date) {
    const diffSec = Math.floor((Date.now() - new Date(date).getTime()) / 1000);
    if (diffSec < 60) return '刚刚';
    if (diffSec < 3600) return `${Math.floor(diffSec / 60)} 分钟前`;
    if (diffSec < 86400) return `${Math.floor(diffSec / 3600)} 小时前`;
    if (diffSec < 2592000) return `${Math.floor(diffSec / 86400)} 天前`;
    return this.format(new Date(date), 'YYYY-MM-DD');
  }
};

export default TimeUtils;
